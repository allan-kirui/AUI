package pl.edu.pg.student.professor.professor.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.student.professor.professor.entity.Professor;
import pl.edu.pg.student.professor.university.entity.University;

import java.util.function.Function;

/**
 * POST Professor request. Contains only fields that can be set up byt the user while creating a new Professor.How
 * Professor is described is defined in {@link pl.edu.pg.student.professor.professor.entity.Professor}
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
     * Professor's total experience.
     */
    private int experience;

    private String university;

    /**
     * @param professionFunction function for converting profession name to profession entity object
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<PostProfessorRequest, Professor> dtoToEntityMapper(
            Function<String, University> professionFunction) {
        return request -> Professor.builder()
                .name(request.getName())
                .age(request.getAge())
                .experience(request.getExperience())
                .education(request.getEducation())
                .university(professionFunction.apply(request.getUniversity()))
                .build();
    }
}
