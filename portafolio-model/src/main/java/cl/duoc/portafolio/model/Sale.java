package cl.duoc.portafolio.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author Matthew
 */
@Entity
@Table(name = "SALE",schema = "PORTAFOLIO")
public class Sale extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_seq_gen")
    @SequenceGenerator(name = "sale_seq_gen", sequenceName = "SALE_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "TOTAL", nullable = false)
    private Long total = null;
    @JoinColumn(name = "PLACE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Place place = null;
    @JoinColumn(name = "VOUCHER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch=FetchType.LAZY)
//    @ManyToOne(optional = false)
    private Voucher voucher = null;
    @JoinColumn(name = "SPECIALVOUCHER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch=FetchType.LAZY)
//    @ManyToOne(optional = false)
    private SpecialVoucher specialVoucher = null;

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

    public SpecialVoucher getSpecialVoucher() {
        return specialVoucher;
    }

    public void setSpecialVoucher(SpecialVoucher specialVoucher) {
        this.specialVoucher = specialVoucher;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Sale other = (Sale) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
