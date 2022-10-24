package pl.edu.pg.eti.kask.rpg.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

/**
 * Repository for University entity. Repositories should be used in business layer (e.g.: in services).
 */
@Repository
public interface UniversityRepository extends JpaRepository<University, String> {

//    /**
//     * Seeks for single university using login and password. Can be use in authentication module.
//     *
//     * @param login    university's login
//     * @param password university's password (hash)
//     * @return container (can be empty) with university
//     */
//    public Optional<University> findByLoginAndPassword(String login, String password) ;

}
