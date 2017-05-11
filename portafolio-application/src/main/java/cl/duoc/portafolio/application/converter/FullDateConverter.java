package cl.duoc.portafolio.application.converter;

import cl.duoc.portafolio.utils.DateUtils;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class FullDateConverter implements Converter, Serializable {

    private static final long serialVersionUID = 8072548563362297856L;
    private static final Logger LOGGER = LoggerFactory.getLogger(FullDateConverter.class);
    private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss.SSS";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String dateStr) {
        Date fullDate = null;
        try {
            if (StringUtils.isNotBlank(dateStr)) {
                fullDate = DateUtils.makeDate(dateStr, DEFAULT_DATE_PATTERN);
            }
        } catch (Exception e) {
            fullDate = null;
            LOGGER.error("Error al parsear string a fecha: {}", e.toString());
        }

        return fullDate;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String result = StringUtils.EMPTY;
        try {
            if (o instanceof java.util.Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
                result = sdf.format(o);
            }
        } catch (Exception e) {
            result = StringUtils.EMPTY;
            LOGGER.error("Problemas para convertir fecha a string: {}", e.toString());
        }
        return result;
    }

}
