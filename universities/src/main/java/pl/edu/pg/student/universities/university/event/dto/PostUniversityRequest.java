package pl.edu.pg.student.universities.university.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.student.universities.university.entity.University;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostUniversityRequest {
    private String name;


    private String location;

    public static Function<University, PostUniversityRequest> dtoToEntityMapper() {
        return entity -> PostUniversityRequest.builder()
                .name(entity.getName())
                .location(entity.getLocation())
                .build();
    }
}
