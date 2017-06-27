package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Place;
import javax.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Matthew
 */
@Resource(name = "placeRepository")
public interface PlaceRepository extends JpaRepository<Place, Long>{
    
    public Place findByName(String name);
}
