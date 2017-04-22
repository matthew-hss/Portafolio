package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.User;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.UserService;
import cl.duoc.portafolio.utils.UserUtils;
import cl.duoc.portafolio.model.Role;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Javier González M.
 */
@Component
@Scope("view")
@Qualifier("userAdminBean")
public class UserAdminBean implements Serializable {

    private static final long serialVersionUID = 2377930989521500160L;

    @Resource(name = "userService")
    private transient UserService userService;
    private User user = null;
    private List<User> users = null;
    private List<Role> roles = null;
    private boolean edit = false;
    private String passOne = null;
    private String passTwo = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminBean.class);

    @PostConstruct
    public void initBean() {
        roles = userService.getRoles();
        refresh();
    }

    private void refresh() {
        user = new User();
        users = userService.getUsers();
        edit = false;
        passOne = StringUtils.EMPTY;
        passTwo = StringUtils.EMPTY;
    }

    public String process() {
        try {
            if (user != null) {
                // Esta variable la usaremos para ver si persistimos o no al usuario
                boolean persist = false;
                /**
                 * Lo primero es determinar si el usuario es nuevo o se está
                 * editando.
                 */
                if (edit) {
                    // Es un usuario antiguo
                    if (StringUtils.isNotBlank(passOne) || StringUtils.isNotBlank(passTwo)) {
                        // Ha modificado la contraseña, deberíamos hashear
                        if (StringUtils.equals(passOne, passTwo)) {
                            persist = true;
                            user = UserUtils.hashPasswd(user, passOne);
                        }
                    } else {
                        // Quiere decir que no se han cambiado las contraseñas, pero igual persisto
                        persist = true;
                    }

                } else {
                    // Es un usuario nuevo
                    if (StringUtils.isNotBlank(passOne) && StringUtils.equals(passOne, passTwo)) {
                        // Las contraseñas deben existir y ser iguales
                        persist = true;
                        user = UserUtils.hashPasswd(user, passOne);
                    }
                }

                if (persist) {
                    User saved = userService.save(user);
                    if (saved != null) {
                        refresh();
                        FacesUtils.infoMessage("saveUserOk");
                    } else {
                        FacesUtils.errorMessage("saveUserNOK");
                    }
                } else {
                    // Si no persisto es porque las contraseñas no cuadran
                    FacesUtils.errorMessage("passwordNotEquals");
                }
            } else {
                FacesUtils.errorMessage("genericSaveError");
            }
        } catch (Exception e) {
            LOGGER.error("Error al intentar persistir usuario: {}", e.toString());
            FacesUtils.fatalMessage("genericSaveError");
        }
        return StringUtils.EMPTY;
    }

    public String editUser() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public String deleteUser() {
        try {
            if (user != null) {
                boolean ok = userService.delete(user);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("deleteUserOk");
                } else {
                    FacesUtils.errorMessage("deleteUserNOK");
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error al intentar eliminar usuario: {}", e.toString());
        }
        return StringUtils.EMPTY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public String getPassOne() {
        return passOne;
    }

    public void setPassOne(String passOne) {
        this.passOne = passOne;
    }

    public String getPassTwo() {
        return passTwo;
    }

    public void setPassTwo(String passTwo) {
        this.passTwo = passTwo;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
