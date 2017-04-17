package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Place;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "saleRepository")
public interface SaleRepository extends JpaRepository<Sale, Long>{
    
    public List<Sale> findByPlace(Place place);
    
    public List<Sale> findByVoucher(Voucher voucher);
}
