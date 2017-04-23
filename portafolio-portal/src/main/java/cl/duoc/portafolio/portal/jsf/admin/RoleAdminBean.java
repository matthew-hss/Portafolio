/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.portal.jsf.admin;

import cl.duoc.portafolio.model.Role;
import cl.duoc.portafolio.portal.utils.FacesUtils;
import cl.duoc.portafolio.service.UserService;
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
@Qualifier("roleAdminBean")
public class RoleAdminBean implements Serializable {

    private static final long serialVersionUID = 559864478748547255L;

    @Resource(name = "userService")
    private transient UserService userService;

    private Role role = null;
    private List<Role> roles = null;
    private boolean edit = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleAdminBean.class);

    @PostConstruct
    public void init() {
        refresh();
    }

    public void refresh() {
        edit = false;
        role = new Role();
        roles = userService.getRoles();
    }

    public String edit() {
        edit = true;
        return StringUtils.EMPTY;
    }

    public String delete() {
        if (role != null) {
            try {
                boolean ok = userService.delete(role);
                if (ok) {
                    refresh();
                    FacesUtils.infoMessage("roleDeleted");
                } else {
                    FacesUtils.errorMessage("roleNotDeleted");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al eliminar rol: {}", e.toString(), e);
                FacesUtils.fatalMessage("roleNotDeleted");
            }
        }
        return StringUtils.EMPTY;
    }
    
    public String process(){
        if(role != null){
            try {
                Role save = userService.save(role);
                if(save!=null){
                    refresh();
                    FacesUtils.infoMessage("roleSaved");
                }else{
                    FacesUtils.infoMessage("roleNotSaved");
                }
            } catch (Exception e) {
                LOGGER.debug("Error al guardar rol: {}", e.toString(), e);
                FacesUtils.fatalMessage("roleNotSaved");
            }
        }
        return StringUtils.EMPTY;                
    }

    public String cancel() {
        refresh();
        return StringUtils.EMPTY;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
}
