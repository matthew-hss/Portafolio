/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
@Resource(name = "productRepository")
public interface ProductRepository extends CrudRepository<Product, Long>{
    
    public List<Product> findAll();
    
    public Product findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Product save(Product role);  
}
