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

import java.util.function.Function;

/**
 * GET Professor response. It contains all field that can be presented (but not necessarily changed) to the used. How
 * Professor is described is defined in {@link pl.edu.pg.eti.kask.rpg.character.entity.Professor}
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetProfessorResponse {
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
    private String university;

    /**
     * Professor's total experience.
     */
    private int experience;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Professor, GetProfessorResponse> entityToDtoMapper() {
        return professor -> GetProfessorResponse.builder()
                .id(professor.getId())
                .name(professor.getName())
                .age(professor.getAge())
                .education(professor.getEducation())
                .experience(professor.getExperience())
                .university(professor.getUniversity().getName())
                .build();
    }

}
