package pl.edu.pg.eti.kask.rpg.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.eti.kask.rpg.digest.Sha256Utility;
import pl.edu.pg.eti.kask.rpg.university.entity.University;
import pl.edu.pg.eti.kask.rpg.university.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding university entity.
 */
@Service
public class UniversityService {

    /**
     * Mock of the database. Should be replaced with repository layer.
     */
    private UniversityRepository repository;

    @Autowired
    public UniversityService(UniversityRepository repository) {
        this.repository = repository;
    }

    /**
     * @param login university's login
     * @return container with university
     */
    public Optional<University> find(String login) {
        return repository.find(login);
    }

    public List<University> findAll() {
        return repository.findAll();
    }

    /**
     * Seeks for single university using login and password. Can be use in authentication module.
     *
     * @param login    university's login
     * @param password university's password (hash)
     * @return container (can be empty) with university
     */

    public Optional<University> find(String login, String password) {
        return repository.findByLoginAndPassword(login, Sha256Utility.hash(password));
    }

    /**
     * Stores new university in the storage.
     *
     * @param university new university
     */
    public void create(University university) {
        repository.create(university);
    }

    public void delete(String universityName) {
        repository.delete(repository.find(universityName).orElseThrow());
    }
}
