package pl.edu.pg.eti.kask.rpg.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.service.ProfessorService;
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

    @Autowired
    public InitializedData(ProfessorService characterService, UniversityService universityService) {
        this.professorService = characterService;
        this.universityService = universityService;
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

            Professor Kyle = Professor.builder()
                    .name("Kyle")
                    .age(54)
                    .education("PG")
                    .experience(1)
                    .university(NYU)
                    .build();

            Professor Hans = Professor.builder()
                    .name("Hans")
                    .age(37)
                    .education("Havard")
                    .experience(16)
                    .university(NYU)
                    .build();

            Professor Loise = Professor.builder()
                    .name("Loise")
                    .age(33)
                    .education("NYU")
                    .experience(12)
                    .university(UCLA)
                    .build();

            Professor Manda = Professor.builder()
                    .name("Manda")
                    .age(64)
                    .education("AGH")
                    .experience(2)
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
