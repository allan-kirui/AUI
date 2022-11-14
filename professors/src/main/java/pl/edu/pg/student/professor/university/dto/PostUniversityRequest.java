package pl.edu.pg.student.professor.university.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pg.student.professor.university.entity.University;

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

    public static Function<PostUniversityRequest, University> dtoToEntityMapper() {
        return request -> University.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();
    }

}
