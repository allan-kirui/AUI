package pl.edu.pg.eti.kask.rpg.character.dto;

import lombok.*;

/**
 * GET subject response. Described details about selected subject. Can be used to present description while
 * professor creation or on professor's stat page. How subject is described is defined in
 * {@link pl.edu.pg.eti.kask.rpg.character.entity.Subject}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSubjectResponse {
    /**
     * Name of the subject.
     */
    private String name;
}
