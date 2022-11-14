package pl.edu.pg.student.universities.university.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import pl.edu.pg.student.universities.university.entity.University;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUniversitiesResponse {
    @Singular
    private List<String> universities;

    public static Function<Collection<University>, GetUniversitiesResponse> entityToDtoMapper() {
        return universities -> {
            GetUniversitiesResponseBuilder response = GetUniversitiesResponse.builder();
            universities.stream()
                    .map(University::getName)
                    .forEach(response::university);
            return response.build();
        };
    }
}
