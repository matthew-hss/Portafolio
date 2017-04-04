/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Product;
import cl.duoc.portafolio.model.Service;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
public interface ServiceRepository extends CrudRepository<Service, Long>{
    
    public List<Service> findAll();
    
    public Service findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Service save(Service role);  
    
    public Service findByProduct(Product product);
}
