package cl.duoc.portafolio.portal.jsf;

import cl.duoc.portafolio.model.User;
import cl.duoc.portafolio.portal.utils.AuthUtils;
import cl.duoc.portafolio.vo.Gender;
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

    private static final Gender MALE = Gender.MALE;
    private static final Gender FEMALE = Gender.FEMALE;
    private boolean admin = false;
    private boolean staff = false;
    private boolean general = false;
    private User user = null;

    @PostConstruct
    public void initBean() {
        user = AuthUtils.getAuthenticateUser();
        admin = AuthUtils.isUserInRole("ROLE_ADMIN");
        staff = AuthUtils.isUserInRole("ROLE_STAFF");
        general = AuthUtils.isUserInRole("ROLE_USER");
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isStaff() {
        return staff;
    }

    public boolean isGeneral() {
        return general;
    }

    public User getUser() {
        return user;
    }

    public Gender getMale() {
        return MALE;
    }

    public Gender getFemale() {
        return FEMALE;
    }
}
