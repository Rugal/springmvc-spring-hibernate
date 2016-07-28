package rugal.sample.core.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author rugal
 */
@Data
@Entity(name = "student")
public class Student
{

    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Column(length = 20)
    private String name;

    @Column
    private Integer age;
}
