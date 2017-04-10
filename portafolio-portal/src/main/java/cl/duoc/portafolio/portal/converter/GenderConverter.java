package cl.duoc.portafolio.portal.converter;

import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.vo.Gender;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class GenderConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String text) {
        Gender gender = Gender.FEMALE;
        if (StringUtils.isNotBlank(text)) {
            String male = StringUtils.trimToEmpty(FacesUtils.getMessage("male"));
            if (StringUtils.equalsIgnoreCase(male, StringUtils.trimToEmpty(text))) {
                gender = Gender.MALE;
            }
        }
        return gender;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String genderStr = StringUtils.EMPTY;
        if (o instanceof Gender) {
            genderStr = StringUtils.trimToEmpty(FacesUtils.getMessage("female"));
            if (Gender.MALE.equals(o)) {
                genderStr = StringUtils.trimToEmpty(FacesUtils.getMessage("male"));
            }
        }
        return genderStr;
    }

}
