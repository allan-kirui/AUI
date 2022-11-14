package pl.edu.pg.student.professor.professor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.student.professor.university.entity.University;
import pl.edu.pg.student.professor.professor.entity.Professor;

import java.util.List;
import java.util.Optional;

/**
 * Repository for character entity. Repositories should be used in business layer (e.g.: in services).
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {


    /**
     * Seeks for single university's professor.
     *
     * @param id         professor's id
     * @param university professor's owner
     * @return container (can be empty) with character
     */
    Optional<Professor> findByIdAndUniversity(Long id, University university);

    /**
     * Seeks for all user's characters.
     *
     * @param university characters' owner
     * @return list (can be empty) of user's characters
     */
    List<Professor> findAllByUniversity(University university);

}
