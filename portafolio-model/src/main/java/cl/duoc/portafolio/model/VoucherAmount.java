package cl.duoc.portafolio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew
 */
@Entity
@Table(name = "voucherAmount")
public class VoucherAmount implements Serializable{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "id", nullable = false)
    private Long id = null;
    @Column(name = "amount", nullable = false)
    private Long amount = null;
    @JoinColumn(name = "function_id", referencedColumnName = "id", nullable = false)
    private Function function = null;
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    private MealService service = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public MealService getService() {
        return service;
    }

    public void setService(MealService service) {
        this.service = service;
    }
    
    
}
