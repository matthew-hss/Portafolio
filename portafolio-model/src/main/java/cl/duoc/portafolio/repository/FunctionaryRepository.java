package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.Functionary;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "functionaryRepository")
public interface FunctionaryRepository extends JpaRepository<Functionary, Long>{
    
    public List<Functionary> findByJobTitle(JobTitle jobTitle);
    
    public Functionary findByRutAndPassword(Integer rut, String password);
    
    public Functionary findByRut(Integer rut);
}
