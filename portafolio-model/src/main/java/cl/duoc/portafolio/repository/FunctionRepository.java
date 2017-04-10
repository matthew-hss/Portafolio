package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Function;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "functionRepository")
public interface FunctionRepository extends JpaRepository<Function, Long>{
    
    public List<Function> findAll();
    
    public Function findById(Long id);
    
//    public boolean delete(Function function);
    
    public Function save(Function role);  
}
