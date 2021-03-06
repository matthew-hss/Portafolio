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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Javier González M.
 */
@Entity
@Table(name = "SPECIALVOUCHER",schema = "PORTAFOLIO")
public class SpecialVoucher extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialvoucher_seq_gen")
    @SequenceGenerator(name = "specialvoucher_seq_gen", sequenceName = "SPECIALVOUCHER_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "CODE", nullable = false, unique = true)
    private String code = null;
    @Column(name = "USED", nullable = false)
    private boolean used = true;
    @Column(name = "DATETIME", nullable = false)
    private Date dateTime = null;
    @JoinColumn(name = "FUNCTIONARY_ID", referencedColumnName = "ID" ,nullable = false)
    @ManyToOne(optional = false)
    private Functionary functionary = null;
    @JoinColumn(name = "SALE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sale sale = null;
    @Column(name = "AMOUNT", nullable = false)
    private Long amount = null;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }  
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final SpecialVoucher other = (SpecialVoucher) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
