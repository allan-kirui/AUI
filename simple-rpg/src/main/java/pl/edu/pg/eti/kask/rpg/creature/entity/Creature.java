package pl.edu.pg.eti.kask.rpg.creature.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
public class Creature implements Serializable {

    /**
     * Unique id (primary key).
     */
    private Long id;

    /**
     * Creature's name.
     */
    private String name;

}
