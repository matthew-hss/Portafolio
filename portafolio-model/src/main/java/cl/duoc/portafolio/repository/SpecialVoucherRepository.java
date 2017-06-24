package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SpecialVoucher;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Javier González M.
 */
@Resource(name = "specialVoucherRepository")
public interface SpecialVoucherRepository extends JpaRepository<SpecialVoucher, Long>{
    
    public List<SpecialVoucher> findByDateTimeBetween(Date start, Date end);
    
    public List<SpecialVoucher> findByFunctionary(Functionary functionary);
    
    public SpecialVoucher findByCode(String code);
    
    public List<SpecialVoucher> findBySale(Sale sale);
    
}
