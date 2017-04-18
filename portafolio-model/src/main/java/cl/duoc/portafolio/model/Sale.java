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
@Table(name = "SALE",schema = "PORTAFOLIO")
public class Sale extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "TOTAL", nullable = false)
    private Long total = null;
    @JoinColumn(name = "PLACE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Place place = null;
    @JoinColumn(name = "VOUCHER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Voucher voucher = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    
    
    
}
