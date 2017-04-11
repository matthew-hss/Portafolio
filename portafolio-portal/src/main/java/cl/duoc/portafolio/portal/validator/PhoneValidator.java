package cl.duoc.portafolio.portal.validator;

import cl.duoc.portafolio.portal.utils.FacesUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Javier González M.
 */
public class PhoneValidator implements Validator {

    private static final String PHONE_PATTERN = "\\d";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            String phoneNum = value.toString();
            if (phoneNum.startsWith("56")) {
                if (phoneNum.length() == 11) {
                    pattern = Pattern.compile(PHONE_PATTERN);
                    matcher = pattern.matcher(phoneNum);
                    if (!matcher.matches()) {
                        String msgError = FacesUtils.getMessage("phoneValidationError");
                        FacesMessage msg = new FacesMessage(msgError);
                        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                        throw new ValidatorException(msg);
                    }
                }
            }
        }
    }
}
