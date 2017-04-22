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
 * @author matthew
 */
@Entity
@Table(name = "WSASSIGNMENT",schema = "PORTAFOLIO")
public class WsAssignment extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wsassignment_seq_gen")
    @SequenceGenerator(name = "wsassignment_seq_gen", sequenceName = "WSASSIGNMENT_SEQ")
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @JoinColumn(name = "WORKSHIFT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Workshift workshift = null;
    @JoinColumn(name = "FUNCTIONARY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Functionary functionary = null;
    @Column(name = "STARTDATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate = null;
    @Column(name = "ENDDATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Workshift getWorkshift() {
        return workshift;
    }

    public void setWorkshift(Workshift workshift) {
        this.workshift = workshift;
    }

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        final WsAssignment other = (WsAssignment) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
