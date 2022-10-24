package pl.edu.pg.eti.kask.rpg.character.dto;

import lombok.*;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * POST Professor request. Contains only fields that can be set up byt the user while creating a new Professor.How
 * Professor is described is defined in {@link pl.edu.pg.eti.kask.rpg.character.entity.Professor} and
 * {@link pl.edu.pg.eti.kask.rpg.creature.entity.Creature} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostProfessorRequest {

    /**
     * Unique id (primary key).
     */
    private Long id;

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
     * Professor's profession (class).
     */
    private String subject;

    /**
     * Professor's total experience.
     */
    private int experience;

    /**
     * @param professionFunction function for converting profession name to profession entity object
     * @param universitySupplier supplier for providing new character owner
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<PostProfessorRequest, Professor> dtoToEntityMapper(
            Function<String, Subject> professionFunction,
            Supplier<University> universitySupplier) {
        return request -> Professor.builder()
                .name(request.getName())
                .age(request.getAge())
                .experience(request.getExperience())
                .education(request.getEducation())
                .subject(professionFunction.apply(request.getSubject()))
                .university(universitySupplier.get())
                .build();
    }
}
