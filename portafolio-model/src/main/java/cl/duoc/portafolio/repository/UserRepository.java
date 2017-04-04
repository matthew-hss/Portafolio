/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Role;
import cl.duoc.portafolio.model.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
@Resource(name = "userRepository")
public interface UserRepository extends CrudRepository<User, Long>{
    
    public List<User> findAll();
    
    public User findById(Long id);
    
    public boolean deleteById(Long id);
    
    public User save(User user);
    
    public List<User> findByRole(Role role);
}
