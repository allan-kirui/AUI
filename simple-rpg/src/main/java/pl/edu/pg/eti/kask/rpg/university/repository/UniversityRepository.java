package pl.edu.pg.eti.kask.rpg.university.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.kask.rpg.datastore.DataStore;
import pl.edu.pg.eti.kask.rpg.repository.Repository;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.util.List;
import java.util.Optional;

/**
 * Repository for University entity. Repositories should be used in business layer (e.g.: in services).
 */
@org.springframework.stereotype.Repository
public class UniversityRepository implements Repository<University, String> {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public UniversityRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<University> find(String id) {
        return store.findUniversity(id);
    }

    @Override
    public List<University> findAll() {
        return store.findAllUniversities();
    }

    @Override
    public void create(University entity) {
        store.createUniversity(entity);
    }

    @Override
    public void delete(University entity) {
        store.deleteUniversity(entity.getName());
    }

    @Override
    public void update(University entity) {
        throw new UnsupportedOperationException("Not implemented.");
    }

    /**
     * Seeks for single university using login and password. Can be use in authentication module.
     *
     * @param login    university's login
     * @param password university's password (hash)
     * @return container (can be empty) with university
     */
    public Optional<University> findByLoginAndPassword(String login, String password) {
        return store.findUniversity(login, password);
    }

}
