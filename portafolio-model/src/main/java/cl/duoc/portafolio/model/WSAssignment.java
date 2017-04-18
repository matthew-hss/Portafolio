package cl.duoc.portafolio.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew
 */
@Entity
@Table(name = "WSASSIGNMENT",schema = "PORTAFOLIO")
public class WSAssignment extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @JoinColumn(name = "WORKSHIFT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Workshift workshift = null;
    @JoinColumn(name = "FUNCTIONARY_ID", referencedColumnName = "ID", nullable = false, unique = true)
    @ManyToOne(optional = false)
    private Functionary functionary = null;
    @Column(name = "STARTDATE", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate = null;
    @Column(name = "ENDDATE", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
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
}
