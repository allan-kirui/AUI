package pl.edu.pg.eti.kask.rpg.character.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.rpg.creature.entity.Creature;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import javax.persistence.*;

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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "professors")
public class Professor extends Creature {

    /**
     * Professor's education story.
     */
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




}
