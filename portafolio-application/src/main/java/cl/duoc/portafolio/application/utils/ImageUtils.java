package cl.duoc.portafolio.application.utils;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matthew
 */
public class ImageUtils implements Serializable {

    private static final long serialVersionUID = 6561723720470099901L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    private ImageUtils() {
        throw new AssertionError();
    }

    public static boolean isImage(byte[] data) {
        boolean ok = false;
        try {
            if (data != null) {
                MagicMatch match = Magic.getMagicMatch(data);
                String mimeType = match.getMimeType();
                ok = StringUtils.contains(mimeType, "image");
                LOGGER.debug(String.format("Tipo detectado: %s", mimeType));
            }
        } catch (MagicException | MagicMatchNotFoundException | MagicParseException e) {
            ok = false;
            LOGGER.error("El archivo elegido no es una imagen: {}", e.toString());
            LOGGER.debug("El archivo elegido no es una imagen: {}", e.toString(), e);
        }
        return ok;
    }

    public static byte[] rescaleImage(byte[] image, int width, int height) {
        byte[] data = null;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(image);
            BufferedImage img = ImageIO.read(in);

            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0, 0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "png", buffer);

            data = buffer.toByteArray();
        } catch (IOException e) {
            data = null;
            LOGGER.error("El archivo elegido no es una imagen: {}", e.toString());
            LOGGER.debug("El archivo elegido no es una imagen: {}", e.toString(), e);
        }
        return data;
    }
}
