package pl.edu.pg.student.professor.university.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import pl.edu.pg.student.professor.professor.entity.Professor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Entity for system university. Represents information about particular university as well as credentials for authorization and
 * authentication needs.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "universities")
public class University implements Serializable {


    /**
     * University's given name.
     */
    @Id
    private String name;

    /**
     * University's location.
     */
    private String location;

    /**
     * List of university's characters.
     */
    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Professor> professors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        University university = (University) o;
        return name != null && Objects.equals(name, university.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
