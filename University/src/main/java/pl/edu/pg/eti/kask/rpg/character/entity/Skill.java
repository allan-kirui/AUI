package pl.edu.pg.eti.kask.rpg.character.entity;

import lombok.*;
import pl.edu.pg.eti.kask.rpg.effect.Effect;

import java.io.Serializable;
import java.util.List;

/**
 * Entity class describing single skill. Skill has its flavour text for name and description and formal list of effects.
 * Effects can not be defined by use (admin) and be stored in database. Effects are predefined in application source
 * code.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Skill implements Serializable {

    /**
     * Name of the skill.
     */
    private String name;

    /**
     * Flavour text description for users.
     */
    private String description;

    /**
     * List of effects associated to this skill. This is not a list of objects because those are not stored in the
     * database but predefined in the application.
     */
    private List<Class<? extends Effect>> effects;

}
