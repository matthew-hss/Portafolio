package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Functionary;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "voucherRepository")
public interface VoucherRepository extends JpaRepository<Voucher, Long>{
    
    public List<Voucher> findByDateTimeBetween(Date start, Date end);
    
    public List<Voucher> findByFunctionary(Functionary functionary);
    
    public List<Voucher> findBySale(Sale sale);
    
}
