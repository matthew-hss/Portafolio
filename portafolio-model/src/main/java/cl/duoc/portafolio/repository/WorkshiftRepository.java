package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Workshift;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "workshiftRepository")
public interface WorkshiftRepository extends JpaRepository<Workshift, Long>{
    
    /**
     *
     * @param startTime Hora inicial del turno
     * @param endTime Hora final del turno
     * @return Una lista de cl.duoc.portafolio.model.Workshift con todos los
     * turnos buscados.
     */
    public List<Workshift> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqualOrderByStartTimeAsc(Date startTime, Date endTime);

}
