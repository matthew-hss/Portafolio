package cl.duoc.portafolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Matthew
 */
@Entity
@Table(name = "VOUCHER",schema = "PORTAFOLIO")
public class Voucher extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "CODE", nullable = false, unique = true)
    private String code = null;
    @Column(name = "USED", nullable = false)
    private boolean used = true;
    @JoinColumn(name = "FUNCTIONARY_ID", referencedColumnName = "ID" ,nullable = false)
    @ManyToOne(optional = false)
    private Functionary functionary = null;
    @JoinColumn(name = "SALE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Sale sale = null;

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
    
    
}
