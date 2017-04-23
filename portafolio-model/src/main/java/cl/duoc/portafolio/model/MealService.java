package cl.duoc.portafolio.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matthew
 */
@Entity
@Table(name = "MEALSERVICE",schema = "PORTAFOLIO")
public class MealService extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mealService_seq_gen")
    @SequenceGenerator(name = "mealService_seq_gen", sequenceName = "MEALSERVICE_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "STARTTIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime = null;
    @Column(name = "ENDTIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime = null;
    @JoinColumn(name = "PRODUCT_ID",referencedColumnName = "ID" ,nullable = false)
    @ManyToOne(optional = false)
    private Product product = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final MealService other = (MealService) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
