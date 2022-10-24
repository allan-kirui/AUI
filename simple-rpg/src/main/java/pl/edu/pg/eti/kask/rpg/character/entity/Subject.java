package pl.edu.pg.eti.kask.rpg.character.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Entity class for game characters' professions (classes). Describes name of the profession and skills available on
 * different levels.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Subject implements Serializable {

    /**
     * Unique id (primary key).
     */
    private Long id;

    /**
     * Name of the profession.
     */
    private String name;


}
