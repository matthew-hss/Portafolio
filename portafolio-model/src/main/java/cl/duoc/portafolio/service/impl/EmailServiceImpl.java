package cl.experti.benefits.service.impl;

import cl.experti.benefits.service.EmailService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sebastián Salazar Molina
 */
@Service("emailService")
public class EmailServiceImpl implements EmailService, Serializable {

    private static final long serialVersionUID = 6579725655302969344L;

    @Resource(name = "mailSender")
    private MailSender mailSender;
    @Value("${mailer.name}")
    private String mailerName;
    @Value("${mailer.email}")
    private String mailerEmail;
    private InternetAddress mailer = null;
    private JavaMailSender sender = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @PostConstruct
    public void init() {
        try {
            LOGGER.info("Iniciando servicio Email");
            this.sender = (JavaMailSender) mailSender;
            if (sender == null) {
                LOGGER.error("No se puede cargar el servicio de Email");
            }

            if (StringUtils.isNotBlank(mailerEmail)) {
                mailer = new InternetAddress(mailerEmail, mailerName);
                LOGGER.info("{} <{}>", mailerName, mailerEmail);
            }
        } catch (Exception e) {
            LOGGER.error("Error al crear servicio de envio de correo: {}", e.toString());
            LOGGER.debug("Error al crear servicio de envio de correo: {}", e.toString(), e);
        }
    }

    @Override
    public boolean sendMail(final String to, final String subject, final String message, final boolean ishtml) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    LOGGER.debug("Preparando envio de correo electronico a {}", to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(mailer);
                    helper.setSubject(subject);
                    helper.setText(message, ishtml);
                    LOGGER.debug("Mensaje de correo: {}", message);
                }
            };

            sender.send(preparator);
            LOGGER.debug("¡Corrreo electronico enviado!");
        } catch (Exception e) {
            result = false;
            LOGGER.error("Error al enviar correo electronico: {}", e.toString());
            LOGGER.debug("Error al enviar correo electronico: {}", e.toString(), e);
        }

        return result;
    }

    @Override
    public boolean sendMail(final String to, final String from, final String subject, final String message, final boolean ishtml) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    LOGGER.debug("Preparando envio de correo electronico a {}", to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setSubject(subject);
                    helper.setText(message, ishtml);
                    LOGGER.debug("Mensaje de correo: {}", message);
                }
            };

            sender.send(preparator);
            LOGGER.debug("¡Corrreo electronico enviado!");
        } catch (Exception e) {
            result = false;
            LOGGER.error("Error al enviar correo electronico: {}", e.toString());
            LOGGER.debug("Error al enviar correo electronico: {}", e.toString(), e);
        }

        return result;
    }

    @Override
    public boolean sendMail(final String to, final String from, final InternetAddress cc, final String subject, final String message, final boolean ishtml) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    LOGGER.debug("Preparando envio de correo electronico a {}", to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setCc(cc);
                    helper.setSubject(subject);
                    helper.setText(message, ishtml);
                    LOGGER.debug("Mensaje de correo: {}", message);
                }
            };

            sender.send(preparator);
            LOGGER.debug("¡Corrreo electronico enviado!");
        } catch (Exception e) {
            result = false;
            LOGGER.error("Error al enviar correo electronico: {}", e.toString());
            LOGGER.debug("Error al enviar correo electronico: {}", e.toString(), e);
        }
        return result;
    }

    @Override
    public boolean sendMail(final String to, final String from, final String subject, final String message, final boolean ishtml, final String path) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    LOGGER.debug("Preparando envio de correo electronico a {}", to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, true, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setSubject(subject);

                    FileSystemResource file = new FileSystemResource(path);
                    if (file.exists()) {
                        helper.addAttachment(file.getFilename(), file.getFile());
                    }

                    helper.setText(message, ishtml);
                    LOGGER.debug("Mensaje de correo: {}", message);
                }
            };

            sender.send(preparator);
            LOGGER.debug("¡Corrreo electronico enviado!");
        } catch (Exception e) {
            result = false;
            LOGGER.error("Error al enviar correo electronico: {}", e.toString());
            LOGGER.debug("Error al enviar correo electronico: {}", e.toString(), e);
        }
        return result;
    }

    @Override
    public boolean sendMail(final String to, final String from, final String subject, final String message, final boolean ishtml, final byte[] file, final String fileName, final String mimeType) {
        boolean result = true;
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mm) throws Exception {

                    LOGGER.debug("Preparando envio de correo electronico a {}", to);
                    MimeMessageHelper helper = new MimeMessageHelper(mm, true, "UTF-8");
                    helper.setTo(to);
                    helper.setFrom(from);
                    helper.setSubject(subject);

                    ByteArrayResource finalFile = new ByteArrayResource(file);
                    if (fileName != null && finalFile.exists()) {
                        helper.addAttachment(fileName, finalFile, mimeType);
                    }

                    helper.setText(message, ishtml);
                    LOGGER.debug("Mensaje de correo: {}", message);
                }
            };

            sender.send(preparator);
            LOGGER.debug("¡Corrreo electronico enviado!");
        } catch (Exception e) {
            result = false;
            LOGGER.error("Error al enviar correo electronico: {}", e.toString());
            LOGGER.debug("Error al enviar correo electronico: {}", e.toString(), e);
        }
        return result;
    }
}
