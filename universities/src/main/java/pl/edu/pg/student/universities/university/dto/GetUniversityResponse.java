package pl.edu.pg.student.universities.university.dto;

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
public class GetUniversityResponse {
    private String name;

    private String location;

    public static Function<University, GetUniversityResponse> entityToDtoMapper() {
        return university -> GetUniversityResponse.builder()
                .name(university.getName())
                .location(university.getLocation())
                .build();
    }
}
