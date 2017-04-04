/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.portafolio.repository;

import cl.duoc.portafolio.model.Function;
import cl.duoc.portafolio.model.Service;
import cl.duoc.portafolio.model.VoucherAmount;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author CETECOM
 */
public interface VoucherAmountRepository extends CrudRepository<VoucherAmount, Long>{
    
    public List<VoucherAmount> findAll();
    
    public VoucherAmount findById(Long id);
    
    public boolean deleteById(Long id);
    
    public VoucherAmount save(VoucherAmount role);  
    
    public List<VoucherAmount> findByFunction(Function function);
    
    public List<VoucherAmount> findByService(Service service);
}
