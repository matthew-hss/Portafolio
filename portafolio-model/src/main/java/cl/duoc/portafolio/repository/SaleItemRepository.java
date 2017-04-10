package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.SaleItem;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "saleItemRepository")
public interface SaleItemRepository extends CrudRepository<SaleItem, Long>{
    
    public List<SaleItem> findAll();
    
    public SaleItem findById(Long id);
    
    public boolean deleteById(Long id);
    
    public SaleItem save(SaleItem role);  
    
    public List<SaleItem> findBySale(Sale sale);
    
    public List<SaleItem> findByProduct(Product product);
}
