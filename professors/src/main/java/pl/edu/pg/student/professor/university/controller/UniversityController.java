package pl.edu.pg.student.professor.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.professor.university.dto.PostUniversityRequest;
import pl.edu.pg.student.professor.university.entity.University;
import pl.edu.pg.student.professor.university.service.UniversityService;

import java.util.Optional;

@RestController
@RequestMapping("api/universities")
public class UniversityController {

    private final UniversityService service;

    @Autowired
    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable("name") String name) {
        Optional<University> university = service.find(name);

        if (university.isPresent()) {
            service.delete(name);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createUniversity(@RequestBody PostUniversityRequest request, UriComponentsBuilder builder) {
        University university = PostUniversityRequest.dtoToEntityMapper().apply(request);

        service.create(university);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "universities", "{name}")
                        .buildAndExpand(university.getName()).toUri())
                .build();
    }
}
