package pl.edu.pg.eti.kask.rpg.university.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

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
public class University implements Serializable {
    /**
     * University's given name.
     */
    private String name;

    /**
     * University's location.
     */
    private String location;

    /**
     * List of university's characters.
     */
    @ToString.Exclude
    private List<Character> characters;

}
