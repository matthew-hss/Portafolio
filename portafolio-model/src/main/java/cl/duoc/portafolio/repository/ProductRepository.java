package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "productRepository")
public interface ProductRepository extends CrudRepository<Product, Long>{
    
    public List<Product> findAll();
    
    public Product findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Product save(Product role);  
}
