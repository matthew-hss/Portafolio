package cl.duoc.portafolio.service;

import javax.mail.internet.InternetAddress;

public interface EmailService {

    /**
     *
     * @param to Correo Electrónico del destinatario
     * @param subject Asunto del correo Electrónico
     * @param message Cuerpo del Mensaje
     * @param ishtml flag que indica si el texto es texto enriquecido (html) o
     * es texto plano.
     * @return true si el correo salió del servidor de correo o false en
     * cualquier otro caso
     */
    public boolean sendMail(final String to, final String subject, final String message, final boolean ishtml);
    
    /**
     *
     * @param to Correo Electrónico del destinatario
     * @param from Correo Electrónico del remitente
     * @param subject Asunto del correo Electrónico
     * @param message Cuerpo del Mensaje
     * @param ishtml flag que indica si el texto es texto enriquecido (html) o
     * es texto plano.
     * @return true si el correo salió del servidor de correo o false en
     * cualquier otro caso
     */
    public boolean sendMail(final String to, final String from, final String subject, final String message, final boolean ishtml);

    /**
     *
     * @param to Correo Electrónico del destinatario
     * @param from Correo Electrónico del remitente
     * @param cc Correo en Copia
     * @param subject Asunto del correo Electrónico
     * @param message Cuerpo del Mensaje
     * @param ishtml flag que indica si el texto es texto enriquecido (html) o
     * es texto plano.
     * @return true si el correo salió del servidor de correo o false en
     * cualquier otro caso
     */
    public boolean sendMail(final String to, final String from, final InternetAddress cc, final String subject, final String message, final boolean ishtml);

    /**
     *
     * @param to Correo Electrónico del destinatario
     * @param from Correo Electrónico del remitente
     * @param subject Asunto del correo Electrónico
     * @param message Cuerpo del Mensaje
     * @param ishtml flag que indica si el texto es texto enriquecido (html) o
     * es texto plano.
     * @param path Ruta física en el servidor al archivo que se adjuntará
     * @return true si el correo salió del servidor de correo o false en
     * cualquier otro caso
     */
    public boolean sendMail(final String to, final String from, final String subject, final String message, final boolean ishtml, final String path);

    /**
     *
     * @param to Correo Electrónico del destinatario
     * @param from Correo Electrónico del remitente
     * @param subject Asunto del correo Electrónico
     * @param message Cuerpo del Mensaje
     * @param ishtml flag que indica si el texto es texto enriquecido (html) o
     * es texto plano.
     * @param file Arreglo de bytes del archivo a adjuntar
     * @param fileName Nombre del archivo
     * @param mimeType tipo mime del archivo
     * @return true si el correo salió del servidor de correo o false en
     * cualquier otro caso
     */
    public boolean sendMail(final String to, final String from, final String subject, final String message, final boolean ishtml, final byte[] file, final String fileName, final String mimeType);
}
