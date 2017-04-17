package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WSAssignment;
import cl.duoc.portafolio.model.Workshift;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "wsassignmentRepository")
public interface WSAssignmentRepository extends JpaRepository<WSAssignment, Long>{
    
    public List<WSAssignment> findByFunctionary(Functionary functionary);
    
    public List<WSAssignment> findByWorkshift(Workshift workshift);
}
