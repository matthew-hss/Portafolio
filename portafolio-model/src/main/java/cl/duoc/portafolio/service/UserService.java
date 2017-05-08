package cl.duoc.portafolio.service;

import cl.duoc.portafolio.vo.Role;
import cl.duoc.portafolio.model.User;
import java.util.List;

/**
 *
 * @author matthew
 */
public interface UserService {

    public User getUser(final Long id);
    
    public User getUser(final Integer rut);

    public List<User> getUsers();

    public List<User> getUsers(final Role role);

    public boolean delete(final User user);

    public User save(final User user);

    public List<Role> getRoles();
    
    public boolean authenticate(final Integer rut, final String password);

}
