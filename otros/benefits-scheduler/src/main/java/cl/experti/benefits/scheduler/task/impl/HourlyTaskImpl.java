package cl.experti.benefits.scheduler.task.impl;

import cl.experti.benefits.model.Beneficiary;
import cl.experti.benefits.model.Company;
import cl.experti.benefits.model.Notification;
import cl.experti.benefits.model.User;
import cl.experti.benefits.scheduler.task.HourlyTask;
import cl.experti.benefits.service.BeneficiaryService;
import cl.experti.benefits.service.CompanyService;
import cl.experti.benefits.service.UserService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina.
 */
@Service("hourlyTask")
public class HourlyTaskImpl implements HourlyTask, Serializable {

    private static final long serialVersionUID = 6481222912599901184L;
    private static final Logger LOGGER = LoggerFactory.getLogger(HourlyTaskImpl.class);

    @Resource(name = "companyService")
    private transient CompanyService companyService;
    @Resource(name = "beneficiaryService")
    private transient BeneficiaryService beneficiaryService;
    @Resource(name = "userService")
    private transient UserService userService;

    @Override
    public void process() {
        try {
            disableCompanyData();
            activeCompanies();
        } catch (Exception e) {
            LOGGER.error("Error al procesar tareas horarias: {}", e.toString());
            LOGGER.debug("Error al procesar tareas horarias: {}", e.toString(), e);
        }
    }

    private void disableCompanyData() {
        LOGGER.debug("Iniciando el proceso de desabilitar datos de compañias desactivadas");
        List<Company> companies = companyService.getCompanies(false);
        if (!companies.isEmpty()) {
            for (Company c : companies) {
                LOGGER.debug("Iniciando el proceso para la compañia " + c.getFantasyName());
                List<User> users = userService.getUsers(c);
                if (users != null && !users.isEmpty()) {
                    LOGGER.debug("Desactivando los usuarios de la compañia: " + c.getFantasyName());
                    for (User u : users) {
                        u.setActive(false);
                        userService.save(u);
                    }
                    LOGGER.info("Todos los usuarios de la compañia: " + c.getFantasyName() + " han sido desactivados");
                }

                List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiaries(c);
                if (beneficiaries != null && !beneficiaries.isEmpty()) {
                    LOGGER.debug("Desactivando los beneficiarios de la compañia: " + c.getFantasyName());
                    for (Beneficiary b : beneficiaries) {
                        b.setActive(false);
                        beneficiaryService.save(b);
                    }
                    LOGGER.info("Todos los beneficiarios de la compañia: " + c.getFantasyName() + " han sido desactivados");
                }

                List<Notification> notifications = companyService.getNotification(c);
                if (notifications != null && !notifications.isEmpty()) {
                    LOGGER.debug("Desactivando las notificaciones de la compañia: " + c.getFantasyName());
                    for (Notification n : notifications) {
                        n.setActive(false);
                        companyService.save(n);
                    }
                    LOGGER.debug("Todas las notificaiones de la compañia: " + c.getFantasyName() + " han sido desactivadas");
                }

                LOGGER.debug("El proceso para la compañia " + c.getFantasyName() + " ha sido terminado.");
            }
        }
    }

    public void activeCompanies() {
        try {
            LOGGER.info("Inicio de tarea horaria Activar Compañía");
            List<Company> companies = companyService.getCompanies(true);
            List<Beneficiary> beneficiaries = null;
            List<Notification> notifications = null;
            List<User> users = null;

            for (Company company : companies) {
                beneficiaries = beneficiaryService.getBeneficiaries(company);
                notifications = companyService.getFutureNotification(company, new Date());
                users = userService.getUsers(company);

                for (Beneficiary beneficiary : beneficiaries) {
                    if (!beneficiary.isActive()) {
                        beneficiary.setActive(true);
                        beneficiaryService.save(beneficiary);
                        LOGGER.info("Beneficiario: {} de compañía: {} actualizado", beneficiary.getNames(), company.getLegalName());
                    }
                }

                for (Notification notification : notifications) {
                    if (!notification.isActive()) {
                        notification.setActive(true);
                        companyService.save(notification);
                        LOGGER.info("Notificación: {} de compañía: {} actualizado", notification.getTitle(), company.getLegalName());
                    }
                }

                for (User user : users) {
                    if (!user.isActive()) {
                        user.setActive(true);
                        userService.save(user);
                        LOGGER.info("Usuario: {} de compañía: {} actualizado", user.getName(), company.getLegalName());
                    }
                }
            }
            LOGGER.info("Término de tarea horaria Activar Compañía");
        } catch (Exception e) {
            LOGGER.error("Error al activar compañias: {}", e.toString());
            LOGGER.debug("Error al activar compañias: {}", e.toString(), e);
        }
    }
}
