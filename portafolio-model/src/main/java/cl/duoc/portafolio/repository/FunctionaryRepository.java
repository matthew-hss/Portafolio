/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Functionary;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
public interface FunctionaryRepository extends CrudRepository<Functionary, Long>{
    
    public List<Functionary> findAll();
    
    public Functionary findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Functionary save(Functionary role);  
    
    public List<Functionary> findByFunction(Function function);
}
