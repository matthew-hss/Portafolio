/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.service;

import cl.duoc.portafolio.model.Role;
import cl.duoc.portafolio.model.User;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface UserService {

    public User getUser(final Long id);

    public List<User> getUsers();

    public List<User> getUsers(final Role role);

    public boolean delete(final User user);

    public User save(final User user);
    
    public Role getRole(final Long id);

    public List<Role> getRoles();

    public boolean delete(final Role role);

    public Role save(final Role role);

}
