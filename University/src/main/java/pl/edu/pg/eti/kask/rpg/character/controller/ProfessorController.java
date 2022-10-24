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
import pl.edu.pg.eti.kask.rpg.character.service.SubjectService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/professors")
public class ProfessorController {

    /**
     * Service for managing professors.
     */
    private ProfessorService professorService;

    /**
     * Service for managing professions.
     */
    private SubjectService subjectService;

    /**
     * @param professorService  service for managing professors
     * @param subjectService service for managing professions
     */
    @Autowired
    public ProfessorController(SubjectService subjectService, ProfessorService professorService) {
        this.professorService = professorService;
        this.subjectService = subjectService;
    }

    /**
     * @return list of professors which will be converted to JSON
     */
    @GetMapping
    public ResponseEntity<GetProfessorsResponse> getProfessors() {
//        return ResponseEntity.ok(GetProfessorsResponse.entityToDtoMapper().apply(professorService.findAll()));
        List<Professor> all = professorService.findAll();
        Function<Collection<Professor>, GetProfessorsResponse> mapper = GetProfessorsResponse.entityToDtoMapper();
        GetProfessorsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    /**
     * @param id id of the professor
     * @return single professor in JSON format or 404 when professor does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<GetProfessorResponse> getProfessor(@PathVariable("id") long id){
        return  professorService.find(id)
                .map(value -> ResponseEntity.ok(GetProfessorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param request new professor parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping
    public ResponseEntity<Void> postProfessor(@RequestBody PostProfessorRequest request, UriComponentsBuilder builder) {
        Professor professor = PostProfessorRequest
                .dtoToEntityMapper(name -> subjectService.find(name).orElseThrow(), () -> null)
                .apply(request);
        professor = professorService.create(professor);
        return ResponseEntity.created(builder.pathSegment("api", "professors", "{id}")
                .buildAndExpand(professor.getId()).toUri()).build();
    }

    /**
     * Deletes selected professor.
     *
     * @param id professor's id
     * @return accepted for not found if professor does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") long id) {
        Optional<Professor> professor = professorService.find(id);
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
     * @param request professor's data parsed from JSON
     * @param id      professor's id
     * @return accepted or not found if professor does not exist
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> putProfessor(@RequestBody PutProfessorRequest request, @PathVariable("id") long id) {
        Optional<Professor> professor = professorService.find(id);
        if (professor.isPresent()) {
            PutProfessorRequest.dtoToEntityUpdater().apply(professor.get(), request);
            professorService.update(professor.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
