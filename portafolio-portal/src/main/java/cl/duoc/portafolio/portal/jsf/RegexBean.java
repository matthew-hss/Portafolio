package cl.duoc.portafolio.portal.jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sebastián Salazar Molina.
 */
@Component
@Scope("session")
@Qualifier("regexBean")
public class RegexBean implements Serializable {

    private static final long serialVersionUID = 6535389655305461760L;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_PATTERN = "^[\\p{L}\\. \'-]+$";
    private static final String TITLE_PATTERN = "^[\\p{L}\\.0-9 \'-]+$";
    private static final String PHONE_PATTERN = "\\d{11}";
    private static final String ADRESS_PATTERN = "[a-zA-Z0-9À-ÖØ-öø-ÿ\\#\\']+\\.?(( |\\-|, )[a-zA-Z0-9À-ÖØ-öø-ÿ\\#\\']+\\.?)*";
    private static final Logger LOGGER = LoggerFactory.getLogger(RegexBean.class);

    @PostConstruct
    public void initBean() {
        LOGGER.info("Patrón para Email: {}", EMAIL_PATTERN);
        LOGGER.info("Patrón para Nombre: {}", NAME_PATTERN);
    }

    public String getEmail() {
        return EMAIL_PATTERN;
    }

    public String getName() {
        return NAME_PATTERN;
    }

    public String getTitle() {
        return TITLE_PATTERN;
    }

    public String getPhone() {
        return PHONE_PATTERN;
    }

    public String getAdress() {
        return ADRESS_PATTERN;
    }
}
