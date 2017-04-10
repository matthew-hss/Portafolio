package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Service;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "serviceRepository")
public interface ServiceRepository extends CrudRepository<Service, Long>{
    
    public List<Service> findAll();
    
    public Service findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Service save(Service role);  
    
    public Service findByProduct(Product product);
}
