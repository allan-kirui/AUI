package pl.edu.pg.student.universities.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.universities.university.entity.University;
import pl.edu.pg.student.universities.university.service.UniversityService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final UniversityService universityService;

    @Autowired
    public InitializedData(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostConstruct
    private synchronized void init() {
        University NYU = University.builder()
                .name("NYU")
                .location("New York")
                .build();

        University UCLA = University.builder()
                .name("UCLA")
                .location("California")
                .build();

        University UoM = University.builder()
                .name("UoM")
                .location("Manchester")
                .build();

        universityService.create(NYU);
        universityService.create(UoM);
        universityService.create(UCLA);
    }
}
