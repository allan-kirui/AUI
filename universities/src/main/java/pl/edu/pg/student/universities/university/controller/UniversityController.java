package pl.edu.pg.student.universities.university.controller;

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
import pl.edu.pg.student.universities.university.dto.GetUniversitiesResponse;
import pl.edu.pg.student.universities.university.dto.GetUniversityResponse;
import pl.edu.pg.student.universities.university.dto.PostUniversityRequest;
import pl.edu.pg.student.universities.university.dto.PutUniversityRequest;
import pl.edu.pg.student.universities.university.entity.University;
import pl.edu.pg.student.universities.university.service.UniversityService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/universities")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<GetUniversitiesResponse> getUniversities() {
        List<University> all = universityService.findAll();
        Function<Collection<University>, GetUniversitiesResponse> mapper = GetUniversitiesResponse.entityToDtoMapper();
        GetUniversitiesResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetUniversityResponse> getUniversity(@PathVariable("name") String name) {
        return universityService.find(name)
                .map(value -> ResponseEntity.ok(GetUniversityResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable("name") String name) {
        Optional<University> university = universityService.find(name);
        if (university.isPresent()) {
            universityService.delete(university.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createProducer(@RequestBody PostUniversityRequest request, UriComponentsBuilder builder) {
        University university = PostUniversityRequest.entityToDtoMapper().apply(request);
        universityService.create(university);
        return ResponseEntity.created(builder.pathSegment("api", "universities", "{name}")
                .buildAndExpand(university.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateUniversity(@RequestBody PutUniversityRequest request, @PathVariable("name") String name) {
        Optional<University> university = universityService.find(name);
        if (university.isPresent()) {
            PutUniversityRequest.dtoToEntityUpdater().apply(university.get(), request);
            universityService.update(university.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
