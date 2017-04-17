package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SaleItem;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "saleItemRepository")
public interface SaleItemRepository extends JpaRepository<SaleItem, Long>{
    
    public List<SaleItem> findBySale(Sale sale);
    
    public List<SaleItem> findByProduct(Product product);
}
