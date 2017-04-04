/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Place;
import cl.duoc.portafolio.model.Sale;
import cl.duoc.portafolio.model.Voucher;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
@Resource(name = "saleRepository")
public interface SaleRepository extends CrudRepository<Sale, Long>{
    
    public List<Sale> findAll();
    
    public Sale findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Sale save(Sale role);  
    
    public List<Sale> findByPlace(Place place);
    
    public List<Sale> findByVoucher(Voucher voucher);
}
