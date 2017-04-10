package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Service;
import cl.duoc.portafolio.model.VoucherAmount;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "voucherAmountRepository")
public interface VoucherAmountRepository extends CrudRepository<VoucherAmount, Long>{
    
    public List<VoucherAmount> findAll();
    
    public VoucherAmount findById(Long id);
    
    public boolean deleteById(Long id);
    
    public VoucherAmount save(VoucherAmount role);  
    
    public List<VoucherAmount> findByFunction(Function function);
    
    public List<VoucherAmount> findByService(Service service);
}