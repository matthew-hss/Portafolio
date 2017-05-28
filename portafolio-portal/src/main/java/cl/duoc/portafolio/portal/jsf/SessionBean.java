package cl.duoc.portafolio.portal.jsf;

import cl.duoc.portafolio.model.User;
import cl.duoc.portafolio.portal.utils.AuthUtils;
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

    private boolean adminRole = false;
    private boolean staffRole = false;
    private boolean userRole = false;
    private User user = null;

    @PostConstruct
    public void initBean() {
        user = AuthUtils.getAuthenticateUser();
        adminRole = AuthUtils.isUserInRole("ROLE_ADMIN");
        staffRole = AuthUtils.isUserInRole("ROLE_STAFF");
        userRole = AuthUtils.isUserInRole("ROLE_USER");
    }

    public boolean isAdminRole() {
        return adminRole;
    }

    public boolean isStaffRole() {
        return staffRole;
    }

    public boolean isUserRole() {
        return userRole;
    }

    public User getUser() {
        return user;
    }
}
