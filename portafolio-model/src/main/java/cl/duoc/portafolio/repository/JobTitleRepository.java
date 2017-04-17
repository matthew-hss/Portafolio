package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.JobTitle;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "jobTitleRepository")
public interface JobTitleRepository extends JpaRepository<JobTitle, Long>{
    
    public JobTitle findByName(String name);
}
