package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "voucherRepository")
public interface VoucherRepository extends CrudRepository<Voucher, Long>{
    
    public List<Voucher> findAll();
    
    public Voucher findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Voucher save(Voucher role);  
    
    public List<Voucher> findByFunctionary(Functionary functionary);
    
    public List<Voucher> findBySale(Sale sale);
    
}
