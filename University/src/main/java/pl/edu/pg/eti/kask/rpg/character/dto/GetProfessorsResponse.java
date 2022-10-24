package pl.edu.pg.eti.kask.rpg.character.dto;

import lombok.*;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * GET professors response. Contains list of available professors. Can be used to list particular user's professors as
 * well as all professors in the game.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetProfessorsResponse {

    /**
     * Represents single professor in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class ProfessorEntry {
        /**
         * Unique id identifying professor.
         */
        private Long id;

        /**
         * Name of the professor.
         */
        private String name;
    }

    /**
     * Name of the selected professors.
     */
    @Singular
    private List<ProfessorEntry> professors;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<Professor>, GetProfessorsResponse> entityToDtoMapper() {
        return professors -> {
            GetProfessorsResponseBuilder response = GetProfessorsResponse.builder();
            professors.stream()
                    .map(professor -> ProfessorEntry.builder()
                            .id(professor.getId())
                            .name(professor.getName())
                            .build())
                    .forEach(response::professor);
            return response.build();
        };
    }
}
