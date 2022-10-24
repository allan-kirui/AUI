package pl.edu.pg.eti.kask.rpg.character.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.rpg.creature.entity.Creature;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

/**
 * Entity for game character owned by the university. Represents characters basic stats (see {@link Creature}) as well as
 * profession and skills. Also contains link to university (see @link {@link University}) for the sake of database relationship.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(callSuper = true)
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
     * Professor's profession (class).
     */
    private Subject subject;

    /**
     * Professor's total experience.
     */
    private int experience;

    /**
     * Creature's portrait. Images in database are stored as blobs (binary large objects).
     */
    @ToString.Exclude
    private byte[] portrait;

    /**
     * Owner of this character.
     */
    private University university;
}
