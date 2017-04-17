package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    public Product findByName(String name);
    
    public List<Product> findByPrice(Integer price);
}
