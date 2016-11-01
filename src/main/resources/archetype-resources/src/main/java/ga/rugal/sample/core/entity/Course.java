package ga.rugal.sample.core.entity;

import com.google.gson.annotations.Expose;
import config.SystemDefaultProperties;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Rugal Bernstein
 */
@Data
@Entity
@Table(name = "course", schema = SystemDefaultProperties.SCHEMA)
public class Course
{

    @Id
    @Basic(optional = false)
    @Expose
    @NotNull
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @Expose
    @Size(max = 20)
    @Column(name = "name", length = 20)
    private String name;

    @OneToMany(mappedBy = "course")
    private List<Registration> registrationList;

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course))
        {
            return false;
        }
        Course other = (Course) object;
        return !((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid)));
    }

    @Override
    public String toString()
    {
        return "ga.rugal.student.core.entity.Course[ cid=" + cid + " ]";
    }

}
