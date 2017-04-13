package cl.duoc.portafolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew
 */
@Entity
@Table(name = "voucherAmount", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"jobtitle_id", "service_id"})
})
public class VoucherAmount extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "id", nullable = false)
    private Long id = null;
    @Column(name = "amount", nullable = false)
    private Long amount = null;
    @JoinColumn(name = "jobtitle_id", referencedColumnName = "id", nullable = false)
    private JobTitle jobTitle = null;
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

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public MealService getService() {
        return service;
    }

    public void setService(MealService service) {
        this.service = service;
    }
    
    
}
