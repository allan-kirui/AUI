package pl.edu.pg.eti.kask.rpg.character.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.kask.rpg.character.dto.GetProfessorResponse;
import pl.edu.pg.eti.kask.rpg.character.dto.GetProfessorsResponse;
import pl.edu.pg.eti.kask.rpg.character.dto.PostProfessorRequest;
import pl.edu.pg.eti.kask.rpg.character.dto.PutProfessorRequest;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.service.ProfessorService;
import pl.edu.pg.eti.kask.rpg.university.entity.University;
import pl.edu.pg.eti.kask.rpg.university.service.UniversityService;

import java.util.Optional;

/**
 * REST controller for university professor resource. It does not return or receive entity objects but dto objects which
 * present only those fields which are converted to JSON. Difference {@link ProfessorController} is that this one
 * defines {@link @PathVariable} for university. Those two could be combined in one controller class but vere separated
 * for logic separation.
 */
@RestController
@RequestMapping("api/universities/{university}/professors")
public class UniversityProfessorController {

    /**
     * Service for managing professors.
     */
    private ProfessorService professorService;

    /**
     * Service for managing universitys.
     */
    private UniversityService universityService;

    /**
     * @param professorService  service for managing professors
     * @param universityService       service for managing universitys
     */
    @Autowired
    public UniversityProfessorController(ProfessorService professorService, UniversityService universityService) {
        this.professorService = professorService;
        this.universityService = universityService;
    }

    /**
     * @param universityname existing university's universityname (login)
     * @return list of universitys' professors which will be converted to JSON or not found if university does not exist
     */
    @GetMapping
    public ResponseEntity<GetProfessorsResponse> getProfessors(@PathVariable("university") String universityname) {
        Optional<University> university = universityService.find(universityname);
        return university.map(value -> ResponseEntity.ok(GetProfessorsResponse.entityToDtoMapper().apply(professorService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProfessorResponse> getProfessor(@PathVariable("university") String universityname,
                                                             @PathVariable("id") long id) {
        return professorService.find(universityname, id)
                .map(value -> ResponseEntity.ok(GetProfessorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




    /**
     * @param universityname existing university's universityname (login)
     * @param request  new professor parsed from JSON
     * @param builder  URI builder
     * @return response with location header or not found if university does not exist
     */
    @PostMapping
    public ResponseEntity<Void> postProfessor(@PathVariable("university") String universityname,
                                              @RequestBody PostProfessorRequest request,
                                              UriComponentsBuilder builder) {
        Optional<University> university = universityService.find(universityname);
        if (university.isPresent()) {
            Professor professor = PostProfessorRequest
                    .dtoToEntityMapper(name -> universityService.find(name).orElseThrow())
                    .apply(request);
            professor = professorService.create(professor);
            return ResponseEntity.created(builder.pathSegment("api", "universities", "{universityname}", "professors", "{id}")
                    .buildAndExpand(university.get().getName(), professor.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes selected professor.
     *
     * @param universityname existing university's universityname (login)
     * @param id       professor's id
     * @return accepted for not found if professor does not exist or not found if university does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("university") String universityname,
                                                @PathVariable("id") long id) {
        Optional<Professor> professor = professorService.find(universityname, id);
        if (professor.isPresent()) {
            professorService.delete(Math.toIntExact(professor.get().getId()));
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates existing professor.
     *
     * @param universityname existing university's universityname (login)
     * @param request  professor's data parsed from JSON
     * @param id       professor's id
     * @return accepted or not found if professor does not exist or not found if university does not exist
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> putProfessor(@PathVariable("university") String universityname,
                                             @RequestBody PutProfessorRequest request,
                                             @PathVariable("id") long id) {
        Optional<Professor> professor = professorService.find(universityname, id);
        if (professor.isPresent()) {
            PutProfessorRequest.dtoToEntityUpdater().apply(professor.get(), request);
            professorService.update(professor.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
