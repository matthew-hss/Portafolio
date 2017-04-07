package cl.duoc.portafolio.portal.converter;

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
 * @author Sebastián Salazar Molina
 */
public class DateCLConverter implements Converter, Serializable {

    private static final long serialVersionUID = 6456706713018848256L;
    private static final Logger LOGGER = LoggerFactory.getLogger(DateCLConverter.class);
    private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String dateStr) {
        Date dateCL = null;
        try {
            if (StringUtils.isNotBlank(dateStr)) {
                dateCL = DateUtils.makeDate(dateStr, DEFAULT_DATE_PATTERN);
            }
        } catch (Exception e) {
            dateCL = null;
            LOGGER.error("Error al parsear string a fecha: {}", e.toString());
        }

        return dateCL;
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
