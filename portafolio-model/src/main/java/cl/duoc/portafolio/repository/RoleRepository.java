package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Role;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "roleRepository")
public interface RoleRepository extends CrudRepository<Role, Long>{
    
    public List<Role> findAll();
    
    public Role findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Role save(Role role);   
}
