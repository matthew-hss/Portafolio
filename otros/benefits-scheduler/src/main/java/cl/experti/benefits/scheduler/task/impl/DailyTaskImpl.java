package cl.experti.benefits.scheduler.task.impl;

import cl.experti.benefits.model.Notification;
import cl.experti.benefits.model.ReadNotification;
import cl.experti.benefits.model.UsedBenefit;
import cl.experti.benefits.scheduler.task.DailyTask;
import cl.experti.benefits.service.ActivationService;
import cl.experti.benefits.service.BenefitService;
import cl.experti.benefits.service.CompanyService;
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
@Service("dailyTask")
public class DailyTaskImpl implements DailyTask, Serializable {

    private static final long serialVersionUID = 8000081738934033408L;
    private static final Logger LOGGER = LoggerFactory.getLogger(DailyTaskImpl.class);

    @Resource(name = "companyService")
    private transient CompanyService companyService;
    @Resource(name = "activationService")
    private transient ActivationService activationService;
    @Resource(name = "benefitService")
    private transient BenefitService benefitService;

    @Override
    public void process() {
        try {
            resetActivations();
            sendNotifications();
            disableUsedBenefit();
        } catch (Exception e) {
            LOGGER.error("Error al procesar tareas diarias: {}", e.toString());
            LOGGER.debug("Error al procesar tareas diarias: {}", e.toString(), e);
        }
    }

    public void sendNotifications() {
        try {
            LOGGER.info("Inicio de tarea Enviar Notificaciones");
            List<Notification> notifications = companyService.getActiveNotification();
            List<ReadNotification> readNotifications = companyService.getReadNotifications();
            for (Notification notification : notifications) {
                for (ReadNotification readNotification : readNotifications) {
                    if (notification.getId().equals(readNotification.getNotification().getId())) {
                        notification.setActive(false);
                        notification.setAllRead(true);
                        companyService.save(notification);
                        LOGGER.info("Notificación: {} enviada", notification.getTitle());
                    }
                }
            }
            LOGGER.info("Término de tarea Enviar Notificaciones");
        } catch (Exception e) {
            LOGGER.error("Error al enviar notificaciones: {}", e.toString());
            LOGGER.debug("Error al enviar notificaciones: {}", e.toString(), e);
        }
    }

    private void disableUsedBenefit() {
        try {
            LOGGER.info("Inicio de tarea Desabilitar Beneficios Usados");
            List<UsedBenefit> usedBenefits = benefitService.getUsedBenefits(new Date());
            for (UsedBenefit usedBenefit : usedBenefits) {
                usedBenefit.setActive(false);
                benefitService.save(usedBenefit);
                LOGGER.info("Beneficio Usado: {} desabilitado", usedBenefit.getBenefit().getTitle());
            }
            LOGGER.info("Término de tarea Desabilitar Beneficios Usados");
        } catch (Exception e) {
            LOGGER.error("Error al desabilitar beneficios usados: {}", e.toString());
            LOGGER.debug("Error al desabilitar beneficios usados: {}", e.toString(), e);
        }
    }

    private void resetActivations() {
        LOGGER.info("Inicio del proceso de RESETEO");
        try {
            boolean ok = activationService.resetActivationsAttemps();
            LOGGER.info("Resultado del reseteo: '{}'", ok);
        } catch (Exception e) {
            LOGGER.error("Error al resetear activaciones: {}", e.toString());
            LOGGER.debug("Error al resetear activaciones: {}", e.toString(), e);
        }
        LOGGER.info("Fin del proceso de Reseteo");
    }
}
