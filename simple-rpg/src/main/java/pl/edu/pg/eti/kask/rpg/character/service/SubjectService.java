package pl.edu.pg.eti.kask.rpg.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.character.repository.SubjectRepository;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding character's profession entity.
 */
@Service
public class SubjectService {

    /**
     * Repository for profession entity.
     */
    private SubjectRepository repository;

    /**
     * @param repository repository for profession entity
     */
    @Autowired
    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    /**
     * @param name name of the profession
     * @return container with profession entity
     */
    public Optional<Subject> find(String name) {
        return repository.find(name);
    }

    /**
     * @param id         character's id
     * @param university existing university
     * @return selected character for university
     */
    public Optional<Professor> find(University university, Long id) {
        return repository.findByIdAndUniversity(id, university);
    }

    /**
     * @param university existing university, character's owner
     * @return all available characters of the selected university
     */
    public List<Professor> findAll(University university) {
        return repository.findAllByUniversity(university);
    }

    /**
     * Stores new subject in the data store.
     *
     * @param subject new subject to be saved
     */
    public void create(Subject subject) {
        repository.create(subject);
    }

    /**
     * @return all available characters
     */
    public List<Subject> findAll() {
        return repository.findAll();
    }

    /**
     * Deletes existing subject.
     *
     * @param companyName existing subject's name to be deleted
     */
    public void delete(String companyName) {
        repository.delete(repository.find(companyName).orElseThrow());
    }

}
