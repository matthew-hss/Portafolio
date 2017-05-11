package cl.duoc.portafolio.application.converter;

import cl.duoc.portafolio.utils.RutUtils;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class RutConverter implements Converter, Serializable {

    private static final long serialVersionUID = 7661978660409026560L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RutConverter.class);

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String result = StringUtils.EMPTY;
        try {
            if (o instanceof String) {
                String rutStr = StringUtils.trimToEmpty((String) o);
                int rutNumber = Integer.parseInt(rutStr);
                if (rutNumber != 0) {
                    result = RutUtils.formatRut(rutNumber);
                }
            }

            if (o instanceof Number) {
                result = RutUtils.formatRut((Number) o);
            }
        } catch (Exception e) {
            result = StringUtils.EMPTY;
            LOGGER.error("Error al determinar el formato del rut: {}", e.toString());
        }
        return result;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        Integer rut = null;
        if (RutUtils.isRut(valor)) {
            rut = RutUtils.parseRut(valor);
        }
        return rut;
    }
}
