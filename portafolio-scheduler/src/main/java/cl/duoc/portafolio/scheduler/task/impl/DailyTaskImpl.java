package cl.duoc.portafolio.scheduler.task.impl;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.SpecialVoucher;
import cl.duoc.portafolio.model.Voucher;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.scheduler.task.DailyTask;
import cl.duoc.portafolio.service.EmailService;
import cl.duoc.portafolio.service.FunctionaryService;
import cl.duoc.portafolio.service.VoucherService;
import cl.duoc.portafolio.utils.DateUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("dailyTask")
public class DailyTaskImpl implements DailyTask, Serializable {

    private static final long serialVersionUID = 8000081738934033408L;
    private static final Logger LOGGER = LoggerFactory.getLogger(DailyTaskImpl.class);

    @Resource(name = "voucherService")
    private transient VoucherService voucherService;
    @Resource(name = "functionaryService")
    private transient FunctionaryService functionaryService;
    @Resource(name = "emailService")
    private transient EmailService emailService;

    @Override
    public void process() {
        try {
            sendEmail();
        } catch (Exception e) {
            LOGGER.error("Error al procesar tareas diarias: {}", e.toString());
            LOGGER.debug("Error al procesar tareas diarias: {}", e.toString(), e);
        }
    }

    public void sendEmail() {
        try {
            LOGGER.info("Inicio de tarea Enviar Correos Electrónicos");
            List<Functionary> functionaries = functionaryService.getFunctionaries();

            for (Functionary functionary : functionaries) {
                int used = 0;
                int notUsed = 0;
                if (functionary.getId() == 1) {
                    List<SpecialVoucher> specialVouchers = voucherService.getSpecialVouchers(functionary);

                    for (SpecialVoucher specialVoucher : specialVouchers) {
                        if (specialVoucher.isUsed()) {
                            used++;
                        } else {
                            notUsed++;
                        }
                    }

                    String message = "Estimado/a Libros Impresos S.A: \nLe informamos que el resumen para ésta semana respecto a sus vales de alimentación es el siguiente: \n\nVales usados: " + used + "\nVales no usados: " + notUsed + "\n\nLe recordamos que los vales que no sean utilizados durante la jornada de trabajo, no podrán ser utilizados, independiente de cual sea el motivo.\n\n\nAtte. Libros Impresos S.A.";
                    emailService.sendMail(functionary.getEmail(), "Información vales de alimentación", message, false);
                    LOGGER.info("Email enviado a: {} ", functionary.getName() + " " + functionary.getSurname());
                } else {

                    WsAssignment wsAssignment = functionaryService.getWsAssignment(functionary);
                    if (wsAssignment != null) {
                        List<Voucher> vouchersFunctionary = voucherService.getVouchers(functionary);

                        for (Voucher voucher : vouchersFunctionary) {
                            if (voucher.isUsed()) {
                                used++;
                            } else {
                                notUsed++;
                            }
                        }

                        int available = (DateUtils.getWorkingDaysBetweenTwoDates(wsAssignment.getStartDate(), wsAssignment.getEndDate())) * 2 - (used + notUsed);

                        String message = "Estimado/a " + functionary.getName() + " " + functionary.getSurname() + ": \nLe informamos que el resumen para ésta semana respecto a sus vales de alimentación es el siguiente: \n\nVales usados: " + used + "\nVales no usados: " + notUsed + "\nVales disponbles: " + available + "\n\nLe recordamos que los vales que no sean utilizados durante la jornada de trabajo, no podrán ser utilizados, independiente de cual sea el motivo.\n\n\nAtte. Libros Impresos S.A.";
                        emailService.sendMail(functionary.getEmail(), "Información vales de alimentación", message, false);
                        LOGGER.info("Email enviado a: {} ", functionary.getName() + " " + functionary.getSurname());
                        LOGGER.info("Total Días: {} ", (DateUtils.getWorkingDaysBetweenTwoDates(wsAssignment.getStartDate(), wsAssignment.getEndDate())));
                        LOGGER.info("Días a restar: {} ", (used + notUsed));
                        LOGGER.info("Disponibles: {} ", available);
                    }
                }
            }
            LOGGER.info("Término de tarea Enviar Correos Electrónicos");
        } catch (Exception e) {
            LOGGER.error("Error al enviar correo electrónico: {}", e.toString());
            LOGGER.debug("Error al enviar correo electrónico: {}", e.toString(), e);
        }
    }
}
