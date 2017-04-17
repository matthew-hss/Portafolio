package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Role;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    public Role findByName(String name);   
}
