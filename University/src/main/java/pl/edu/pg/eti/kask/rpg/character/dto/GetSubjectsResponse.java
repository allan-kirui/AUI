package pl.edu.pg.eti.kask.rpg.character.dto;

import lombok.*;

import java.util.List;

/**
 * GET professions response. Returns list of all available professions names.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetSubjectsResponse {
    /**
     * List of all subjects names.
     */
    private List<String> subjects;
}
