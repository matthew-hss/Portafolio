package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Role;
import cl.duoc.portafolio.model.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "userRepository")
public interface UserRepository extends CrudRepository<User, Long>{
    
    public List<User> findAll();
    
    public User findById(Long id);
    
    public User findByRut(Integer rut);
    
    public boolean deleteById(Long id);
    
    public User save(User user);
    
    public List<User> findByRole(Role role);
    
    public User findByRutAndPassword(Integer rut, String password);
}
