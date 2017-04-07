package cl.duoc.portafolio.portal.converter;

import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Javier González M.
 */
public class TruncateConverter implements Converter, Serializable {

    private static final long serialVersionUID = 6139229876331968512L;

//    private int truncateAt = 0;
//    private String continuationMark;
    private int truncateAt = 128;
    private String continuationMark = "...";

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String texto) {
        String resp = texto;

        if (truncateAt > 0 && resp.length() > truncateAt) {
            resp = resp.substring(0, truncateAt);
            if (continuationMark != null) {
                resp += continuationMark;
            }
        }

        return StringUtils.trimToEmpty(resp);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            String resp = o.toString();

            if (truncateAt > 0 && resp.length() > truncateAt) {
                resp = resp.substring(0, truncateAt);
                if (continuationMark != null) {
                    resp += continuationMark;
                }
            }
            return StringUtils.trimToEmpty(resp);
        } else {
            return StringUtils.EMPTY;
        }
    }

}
