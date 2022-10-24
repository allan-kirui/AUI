package pl.edu.pg.eti.kask.rpg.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.character.service.ProfessorService;
import pl.edu.pg.eti.kask.rpg.character.service.SubjectService;
import pl.edu.pg.eti.kask.rpg.university.entity.University;
import pl.edu.pg.eti.kask.rpg.university.service.UniversityService;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin university.
 */
@Component
public class InitializedData {

    /**
     * Service for characters operations.
     */
    private final ProfessorService professorService;

    /**
     * Service for users operations.
     */
    private final UniversityService universityService;

    /**
     * Service for professions operations.
     */
    private final SubjectService professionService;

    @Autowired
    public InitializedData(ProfessorService characterService, UniversityService universityService, SubjectService professionService) {
        this.professorService = characterService;
        this.universityService = universityService;
        this.professionService = professionService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {
        {
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

            Subject Physics = Subject.builder().name("Physics").build();
            Subject Maths = Subject.builder().name("Maths").build();
            Subject Chemistry = Subject.builder().name("Chemistry").build();
            Subject Medicine = Subject.builder().name("Medicine").build();

            professionService.create(Physics);
            professionService.create(Maths);
            professionService.create(Chemistry);
            professionService.create(Medicine);

            Professor Kyle = Professor.builder()
                    .name("Kyle")
                    .age(54)
                    .education("PG")
                    .experience(1)
                    .subject(Physics)
                    .portrait(getResourceAsByteArray("avatar/calvian.png"))//package relative path
                    .university(NYU)
                    .build();

            Professor Hans = Professor.builder()
                    .name("Hans")
                    .age(37)
                    .education("Havard")
                    .experience(16)
                    .subject(Medicine)
                    .portrait(getResourceAsByteArray("avatar/uhlbrecht.png"))//package relative path
                    .university(NYU)
                    .build();

            Professor Loise = Professor.builder()
                    .name("Loise")
                    .age(33)
                    .education("NYU")
                    .experience(12)
                    .subject(Chemistry)
                    .portrait(getResourceAsByteArray("avatar/eloise.png"))//package relative path
                    .university(UCLA)
                    .build();

            Professor Manda = Professor.builder()
                    .name("Manda")
                    .age(64)
                    .education("AGH")
                    .experience(2)
                    .subject(Medicine)
                    .portrait(getResourceAsByteArray("avatar/zereni.png"))//package relative path
                    .university(UoM)
                    .build();

            professorService.create(Kyle);
            professorService.create(Manda);
            professorService.create(Loise);
            professorService.create(Hans);
        }
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
