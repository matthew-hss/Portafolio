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
@Table(name = "FUNCTIONARY",schema = "PORTAFOLIO")
public class Functionary extends BaseBean{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "functionary_seq_gen")
    @SequenceGenerator(name = "functionary_seq_gen", sequenceName = "FUNCTIONARY_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "PASSWORD", nullable =false)
    private String password = null;
    @Column(name = "RUT", nullable =false, unique = true)
    private Integer rut = null;
    @Column(name = "NAME", nullable =false)
    private String name = null;
    @Column(name = "SURNAME", nullable =false)
    private String surname = null;
    @JoinColumn(name = "JOBTITLE_ID", referencedColumnName = "ID", nullable =false)
    @ManyToOne(optional = false)
    private JobTitle jobTitle = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Functionary other = (Functionary) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
