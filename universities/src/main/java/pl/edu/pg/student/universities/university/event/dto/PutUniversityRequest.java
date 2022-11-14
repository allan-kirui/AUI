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

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutUniversityRequest {
    private String name;
    private String location;

    public static BiFunction<University, pl.edu.pg.student.universities.university.dto.PutUniversityRequest, University> dtoToEntityUpdater() {
        return (producer, request) -> {
            producer.setName(request.getName());
            producer.setLocation(request.getLocation());
            return producer;
        };
    }
}
