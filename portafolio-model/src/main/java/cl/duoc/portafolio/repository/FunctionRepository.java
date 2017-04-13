package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.JobTitle;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "functionRepository")
public interface FunctionRepository extends JpaRepository<JobTitle, Long>{
    
    public List<JobTitle> findAll();
    
    public JobTitle findById(Long id);
    
//    public boolean delete(JobTitle function);
    
    public JobTitle save(JobTitle role);  
}
