package cl.duoc.portafolio.portal.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class TrimConverter implements Converter, Serializable {

    private static final long serialVersionUID = 6139229856331968512L;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String texto) {
        return StringUtils.trimToEmpty(texto);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return StringUtils.trimToEmpty(o.toString());
        } else {
            return StringUtils.EMPTY;
        }
    }
}
