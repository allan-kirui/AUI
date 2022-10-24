package pl.edu.pg.eti.kask.rpg.character.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.datastore.DataStore;
import pl.edu.pg.eti.kask.rpg.repository.Repository;
import pl.edu.pg.eti.kask.rpg.serialization.CloningUtility;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * Repository for character entity. Repositories should be used in business layer (e.g.: in services).
 */
@org.springframework.stereotype.Repository
public class ProfessorRepository implements Repository<Professor, Long> {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public ProfessorRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Professor> find(Long id) {
        return store.findProfessor(id);
    }

    @Override
    public List<Professor> findAll() {
        return store.findAllProfessors();
    }

    @Override
    public void create(Professor entity) {
        store.createProfessor(entity);
    }

    @Override
    public void delete(Professor entity) {
        store.deleteProfessor(entity.getId());
    }

    @Override
    public void update(Professor entity) {
        store.updateProfessor(entity);
    }

    /**
     * Seeks for single university's professor.
     *
     * @param id         professor's id
     * @param university professor's owner
     * @return container (can be empty) with character
     */
    public Optional<Professor> findByIdAndUser(Long id, University university) {
        return store.getCharacterStream()

                .filter(professor -> professor.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

}
