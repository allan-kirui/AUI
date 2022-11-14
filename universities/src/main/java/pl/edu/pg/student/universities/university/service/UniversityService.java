package pl.edu.pg.student.universities.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.student.universities.university.entity.University;
import pl.edu.pg.student.universities.university.event.repository.UniversityEventRepository;
import pl.edu.pg.student.universities.university.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository repository;
    private final UniversityEventRepository eventRepository;

    @Autowired
    public UniversityService(UniversityRepository repository, UniversityEventRepository eventRepository){
        this.repository = repository;
        this.eventRepository = eventRepository;
    }
    public Optional<University> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public void create(University university) {
        repository.save(university);
        eventRepository.create(university);
    }

    public List<University> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(University university) {
        eventRepository.delete(university);
        repository.delete(university);
    }

    @Transactional
    public void update(University university) {
        repository.save(university);
    }

}
