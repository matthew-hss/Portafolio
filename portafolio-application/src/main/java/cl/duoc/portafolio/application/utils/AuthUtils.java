package cl.duoc.portafolio.application.utils;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.User;
import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Sebastián Salazar Molina.
 */
public class AuthUtils implements Serializable {

    private static final long serialVersionUID = 6561723720470099968L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUtils.class);

    private AuthUtils() {
        throw new AssertionError();
    }

    public static boolean isUserInRole(String role) {
        boolean ok = false;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                ok = authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
            }
        } catch (Exception e) {
            ok = false;
            LOGGER.error("Error al determinar Roles de Usuario: {}", e.toString());
        }
        return ok;
    }

    public static User getAuthenticateUser() {
        User user = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                user = (User) authentication.getPrincipal();
            }
        } catch (Exception e) {
            user = null;
            LOGGER.error("Error al determinar usuario: {}", e.toString());
        }
        return user;
    }

    public static Functionary getAuthenticateFunctionary() {
        Functionary functionary = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                functionary = (Functionary) authentication.getPrincipal();
            }
        } catch (Exception e) {
            functionary = null;
            LOGGER.error("Error al determinar funcionario: {}", e.toString());
        }
        return functionary;
    }
}
