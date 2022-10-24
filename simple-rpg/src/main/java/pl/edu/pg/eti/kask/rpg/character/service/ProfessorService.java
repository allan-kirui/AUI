package pl.edu.pg.eti.kask.rpg.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.repository.ProfessorRepository;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding professor entity.
 */
@Service
public class ProfessorService {

    /**
     * Repository for professor entity.
     */
    private ProfessorRepository repository;


    /**
     * @param repository repository for professor entity
     */
    @Autowired
    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds single professor.
     *
     * @param id professor's id
     * @return container with professor
     */
    public Optional<Professor> find(Long id) {
        return repository.find(id);
    }

    /**
     * @param id         professor's id
     * @param university existing university
     * @return selected professor for university
     */
    public Optional<Professor> find(University university, Long id) {
        return repository.findByIdAndUser(id, university);
    }

    /**
     * @return all available professors
     */
    public List<Professor> findAll() {
        return repository.findAll();
    }

    /**
     * Creates new professor.
     *
     * @param professor new professor
     */
    public void create(Professor professor) {
        repository.create(professor);
    }

    /**
     * Updates existing professor.
     *
     * @param professor professor to be updated
     */
    public void update(Professor professor) {
        repository.update(professor);
    }

    /**
     * Deletes existing professor.
     *
     * @param employeeID existing employee's id to be deleted
     */
    public void delete(int employeeID) {
        repository.delete(repository.find((long) employeeID).orElseThrow());
    }

    /**
     * Updates portrait of the employee.
     *
     * @param id professor's id
     * @param is input stream containing new portrait
     */
    public void updatePortrait(Long id, InputStream is) {
        repository.find(id).ifPresent(employee -> {
            try {
                employee.setPortrait(is.readAllBytes());
                repository.update(employee);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

}
