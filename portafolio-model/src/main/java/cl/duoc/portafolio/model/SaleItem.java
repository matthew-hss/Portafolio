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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew
 */
@Entity
@Table(name = "SALEITEM",schema = "PORTAFOLIO")
public class SaleItem extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "saleitem_seq_gen")
    @SequenceGenerator(name = "saleitem_seq_gen", sequenceName = "SALEITEM_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Product product = null;
    @JoinColumn(name = "SALE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Sale sale = null;
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final SaleItem other = (SaleItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
