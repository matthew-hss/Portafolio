package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.vo.Role;
import cl.duoc.portafolio.model.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "userRepository")
public interface UserRepository extends JpaRepository<User, Long>{
    
    public User findByRut(Integer rut);
    
    public List<User> findByRole(Role role);
    
    public User findByRutAndPassword(Integer rut, String password);
}
