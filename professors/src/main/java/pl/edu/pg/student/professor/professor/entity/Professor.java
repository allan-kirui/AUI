package pl.edu.pg.student.professor.professor.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import pl.edu.pg.student.professor.university.entity.University;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity for game character owned by the university. Represents characters basic stats (see {@link Creature}) as well as
 * profession and skills. Also contains link to university (see @link {@link University}) for the sake of database relationship.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@Entity
@Table(name = "professors")
public class Professor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String education;

    /**
     * Professor's age.
     */
    private int age;

    /**
     * Employer of this character.
     */
    @ManyToOne
    @JoinColumn(name = "university")
    private University university;


    /**
     * Professor's total experience.
     */
    private int experience;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Professor professor = (Professor) o;
        return id != null && Objects.equals(id, professor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
