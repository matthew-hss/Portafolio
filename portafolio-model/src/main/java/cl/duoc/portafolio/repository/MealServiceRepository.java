package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.MealService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "mealServiceRepository")
public interface MealServiceRepository extends CrudRepository<MealService, Long>{
    
    public List<MealService> findAll();
    
    public MealService findById(Long id);
    
    public boolean deleteById(Long id);
    
    public MealService save(MealService role);  
    
    public MealService findByProduct(Product product);
}
