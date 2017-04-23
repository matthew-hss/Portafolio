package cl.duoc.portafolio.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author matthew
 */
@Entity
@Table(name = "JOBTITLE",schema = "PORTAFOLIO")
public class JobTitle implements Serializable{
    private static final long serialVersionUID = 7226055427557870592L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobtitle_seq_gen")
    @SequenceGenerator(name = "jobtitle_seq_gen", sequenceName = "JOBTITLE_SEQ", allocationSize = 1)
    @XmlTransient
    @Column(name = "ID", nullable = false)
    private Long id = null;
    @Column(name = "NAME", nullable = false, unique = true)
    private String name = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final JobTitle other = (JobTitle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
