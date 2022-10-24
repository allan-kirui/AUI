package pl.edu.pg.eti.kask.rpg.character.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;

/**
 * Repository for profession entity. Repositories should be used in business layer (e.g.: in services).
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {


}
