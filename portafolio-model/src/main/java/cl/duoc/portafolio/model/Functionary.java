package cl.duoc.portafolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    
    
}
