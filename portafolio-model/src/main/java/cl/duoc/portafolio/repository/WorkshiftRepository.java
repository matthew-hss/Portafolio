/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Workshift;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
@Resource(name = "workshiftRepository")
public interface WorkshiftRepository extends CrudRepository<Workshift, Long>{
    
    public List<Workshift> findAll();
    
    public Workshift findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Workshift save(Workshift role);  
}
