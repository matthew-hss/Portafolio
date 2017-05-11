package cl.duoc.portafolio.application.jsf;

import cl.duoc.portafolio.application.utils.AuthUtils;
import cl.duoc.portafolio.model.Functionary;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Javier González M.
 */
@Component
@Scope("session")
@Qualifier("sessionBean")
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 8607031103703517184L;
    
    private Functionary functionary = null;

    @PostConstruct
    public void initBean() {
        functionary = AuthUtils.getAuthenticateFunctionary();
    }

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }
    
}
