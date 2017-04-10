package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Place;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "placeRepository")
public interface PlaceRepository extends CrudRepository<Place, Long>{
    
    public List<Place> findAll();
    
    public Place findById(Long id);
    
    public boolean deleteById(Long id);
    
    public Place save(Place role);  
}
