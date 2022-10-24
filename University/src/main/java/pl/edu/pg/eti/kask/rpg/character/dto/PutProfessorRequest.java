package pl.edu.pg.eti.kask.rpg.character.dto;

import lombok.*;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;

import java.util.function.BiFunction;

/**
 * PUT Professor request. Contains all fields that can be updated by the user. .How Professor is described is defined in
 * {@link pl.edu.pg.eti.kask.rpg.character.entity.Professor} and {@link pl.edu.pg.eti.kask.rpg.creature.entity.Creature}
 * classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutProfessorRequest {
    /**
     * Creature's name.
     */
    private String name;

    /**
     * Professor's education story.
     */
    private String education;

    /**
     * Professor's age.
     */
    private int age;

    /**
     * Professor's total experience.
     */
    private int experience;

    /**
     * @return updater for convenient updating entity object using dto object
     */
    public static BiFunction<Professor, PutProfessorRequest, Professor> dtoToEntityUpdater() {
        return (professor, request) -> {
            professor.setName(request.getName());
            professor.setAge(request.getAge());
            professor.setEducation(request.getEducation());
            professor.setExperience(request.getExperience());
            return professor;
        };
    }
}
