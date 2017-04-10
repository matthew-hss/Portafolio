package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Functionary;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "functionaryRepository")
public interface FunctionaryRepository extends CrudRepository<Functionary, Long>{
    
    public List<Functionary> findAll();
    
    public Functionary findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Functionary save(Functionary functionary);  
    
    public List<Functionary> findByFunction(Function function);
}
