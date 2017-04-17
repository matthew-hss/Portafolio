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
     * @param startDate Fecha inicial, la startDate de los beneficios debe ser
     * igual o mayor
     * @param endDate Fecha final, la endDate de los beneficios debe ser igual o
     * menor
     * @return Una lista de cl.duoc.portafolio.model.Workshift con todos los
     * turnos buscados.
     */
    public List<Workshift> findByStartDateGreaterThanEqualAndEndDateLessThanEqualOrderByEndDateDesc(Date startDate, Date endDate);

}
