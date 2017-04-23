package cl.duoc.portafolio.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew
 */
@Entity
@Table(name = "VOUCHERAMOUNT", schema = "PORTAFOLIO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"MEALSERVICE_ID","JOBTITLE_ID"})
})
public class VoucherAmount extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voucheramount_seq_gen")
    @SequenceGenerator(name = "voucheramount_seq_gen", sequenceName = "VOUCHERAMOUNT_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount = null;
    @JoinColumn(name = "JOBTITLE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private JobTitle jobTitle = null;
    @JoinColumn(name = "MEALSERVICE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private MealService mealService = null;

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

    public MealService getMealService() {
        return mealService;
    }

    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VoucherAmount other = (VoucherAmount) obj;
        if (!Objects.equals(this.jobTitle, other.jobTitle)) {
            return false;
        }
        if (!Objects.equals(this.mealService, other.mealService)) {
            return false;
        }
        return true;
    }
    
    
}
