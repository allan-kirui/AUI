package pl.edu.pg.student.universities.university.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.student.universities.university.entity.University;
import pl.edu.pg.student.universities.university.event.dto.PostUniversityRequest;

@Repository
public class UniversityEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public UniversityEventRepository(@Value("${professor.professors.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(University university) {
        restTemplate.delete("/universities/{name}", university.getName());
    }

    public void create(University university) {
        restTemplate.postForLocation("/universities", PostUniversityRequest.dtoToEntityMapper().apply(university));
    }
}
