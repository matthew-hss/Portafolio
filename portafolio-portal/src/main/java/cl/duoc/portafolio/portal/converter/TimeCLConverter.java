package cl.duoc.portafolio.portal.converter;

import cl.duoc.portafolio.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class TimeCLConverter implements Converter, Serializable {

    private static final long serialVersionUID = 2917826408105129554L;
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeCLConverter.class);
    private static final String DEFAULT_TIME_PATTERN = "HH:mm:ss.SSS";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String timeStr) {
        Date hour = null;
        try {
            if (StringUtils.isNotBlank(timeStr)) {
                hour = DateUtils.makeDate(timeStr, DEFAULT_TIME_PATTERN);
            }
        } catch (Exception e) {
            hour = null;
            LOGGER.error("Error al parsear string a hora", e.toString());
        }
        return hour;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String hourStr = StringUtils.EMPTY;
        try {
            if (o instanceof java.util.Date) {
                DateFormat fmt = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
                hourStr = fmt.format(o);
            }
        } catch (Exception e) {
            hourStr = StringUtils.EMPTY;
            LOGGER.error("Error al convertir hora a string: {}", e.toString());
        }
        return hourStr;
    }
}
