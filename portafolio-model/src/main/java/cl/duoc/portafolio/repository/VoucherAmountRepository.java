package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.JobTitle;
import cl.duoc.portafolio.model.MealService;
import cl.duoc.portafolio.model.VoucherAmount;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "voucherAmountRepository")
public interface VoucherAmountRepository extends JpaRepository<VoucherAmount, Long>{
    
    public VoucherAmount findByJobTitleAndMealService(JobTitle function, MealService mealService);
    
    public List<VoucherAmount> findByJobTitle(JobTitle function);
    
    public List<VoucherAmount> findByMealService(MealService service);
}
