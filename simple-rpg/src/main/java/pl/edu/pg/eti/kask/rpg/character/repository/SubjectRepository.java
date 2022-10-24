package pl.edu.pg.eti.kask.rpg.character.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.datastore.DataStore;
import pl.edu.pg.eti.kask.rpg.repository.Repository;
import pl.edu.pg.eti.kask.rpg.serialization.CloningUtility;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for profession entity. Repositories should be used in business layer (e.g.: in services).
 */
@org.springframework.stereotype.Repository
public class SubjectRepository implements Repository<Subject, String> {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public SubjectRepository(DataStore store) {
        this.store = store;
    }


    @Override
    public Optional<Subject> find(String id) {
        return store.findSubject(id);
    }

    @Override
    public List<Subject> findAll() {
        return store.findAllSubjects();
    }

    @Override
    public void create(Subject entity) {
        store.createSubject(entity);
    }

    @Override
    public void delete(Subject entity) {
        store.deleteSubject(entity.getName());
    }

    @Override
    public void update(Subject entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    /**
     * Seeks for single university's character.
     *
     * @param id         subject's id
     * @param university subject's owner
     * @return container (can be empty) with character
     */
    public Optional<Professor> findByIdAndUniversity(Long id, University university) {
        return store.findAllProfessors().stream()
                .filter(professor -> professor.getUniversity().equals(university))
                .filter(professor -> professor.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Seeks for all university's professor.
     *
     * @param university professor' owner
     * @return list (can be empty) of university's professor
     */
    public List<Professor> findAllByUniversity(University university) {
        return store.findAllProfessors().stream()
                .filter(professor -> professor.getUniversity().equals(university))
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

}
