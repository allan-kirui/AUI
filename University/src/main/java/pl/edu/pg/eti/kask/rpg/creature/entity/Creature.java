package pl.edu.pg.eti.kask.rpg.creature.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity for game creature. Represents all creatures that can be found in the game as well as is base class for are
 * character classes and possible NPCs.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "creatures")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Creature implements Serializable {

    /**
     * Unique id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    /**
     * Creature's name.
     */
    private String name;

}
