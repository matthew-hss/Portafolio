/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Function;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
@Resource(name = "functionRepository")
public interface FunctionRepository extends CrudRepository<Function, Long>{
    
    public List<Function> findAll();
    
    public Function findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Function save(Function role);  
}
