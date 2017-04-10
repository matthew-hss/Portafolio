package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WSAssignment;
import cl.duoc.portafolio.model.Workshift;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "wsassignmentRepository")
public interface WSAssignmentRepository extends CrudRepository<WSAssignment, Long>{
    
    public List<WSAssignment> findAll();
    
    public WSAssignment findById(Long id);
    
    public boolean deleteById(Long id);
    
    public WSAssignment save(WSAssignment role);  
    
    public List<WSAssignment> findByFunctionary(Functionary functionary);
    
    public List<WSAssignment> findByWorkshift(Workshift workshift);
}