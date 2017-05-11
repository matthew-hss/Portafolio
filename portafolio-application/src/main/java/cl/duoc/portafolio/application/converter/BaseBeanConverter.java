package cl.duoc.portafolio.application.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina
 */
public class BaseBeanConverter implements Converter, Serializable {

    private static final long serialVersionUID = 6802811478551171072L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBeanConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String text) {
        Object result = null;
        try {
            if (StringUtils.isNotBlank(text)) {
                byte[] serObj = Hex.decodeHex(text.toCharArray());
                result = SerializationUtils.deserialize(serObj);
            }
        } catch (Exception e) {
            result = null;
            LOGGER.error("Error al deserializar el objeto: {}", e.toString());
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
        String result = StringUtils.EMPTY;
        try {
            if (obj != null) {
                byte[] serObj = SerializationUtils.serialize((Serializable) obj);
                result = new String(Hex.encodeHex(serObj));
            }
        } catch (Exception e) {
            result = StringUtils.EMPTY;
            LOGGER.error("Error al serializar objeto: {}", e.toString());
        }
        return result;
    }
}
