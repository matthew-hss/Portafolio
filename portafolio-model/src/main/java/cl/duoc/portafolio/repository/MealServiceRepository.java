package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.MealService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "mealServiceRepository")
public interface MealServiceRepository extends JpaRepository<MealService, Long>{
    
    public MealService findByProduct(Product product);
}
