package pl.edu.pg.eti.kask.rpg.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.character.repository.SubjectRepository;

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
        return repository.findById(name);
    }

    @Transactional
    public void create(Subject subject) {
        repository.save(subject);
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }

    @Transactional
    public void update(Subject subject) {
        repository.save(subject);
    }

    public List<Subject> findAll(){
        return repository.findAll();
    }


}
