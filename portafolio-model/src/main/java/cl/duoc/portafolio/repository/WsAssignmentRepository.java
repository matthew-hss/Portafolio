package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.WsAssignment;
import cl.duoc.portafolio.model.Workshift;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "wsAssignmentRepository")
public interface WsAssignmentRepository extends JpaRepository<WsAssignment, Long>{
    
    public List<WsAssignment> findByFunctionary(Functionary functionary);
    
    public List<WsAssignment> findByWorkshift(Workshift workshift);
}
