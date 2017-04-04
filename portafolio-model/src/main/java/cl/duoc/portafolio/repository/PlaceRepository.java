/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Place;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
@Resource(name = "placeRepository")
public interface PlaceRepository extends CrudRepository<Place, Long>{
    
    public List<Place> findAll();
    
    public Place findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Place save(Place role);  
}
