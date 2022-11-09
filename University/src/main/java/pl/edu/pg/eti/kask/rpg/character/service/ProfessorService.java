package pl.edu.pg.eti.kask.rpg.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.repository.ProfessorRepository;
import pl.edu.pg.eti.kask.rpg.university.entity.University;
import pl.edu.pg.eti.kask.rpg.university.repository.UniversityRepository;

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
    private ProfessorRepository professorRepository;


    /**
     * Repository for user entity.
     */
    private UniversityRepository universityRepository;

    /**
     * @param professorRepository  professorRepository for professor entity
     * @param universityRepository repository for user entity
     */
    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, UniversityRepository universityRepository) {
        this.professorRepository = professorRepository;
        this.universityRepository = universityRepository;
    }

    /**
     * Finds single professor.
     *
     * @param id professor's id
     * @return container with professor
     */
    public Optional<Professor> find(Long id) {
        return professorRepository.findById(id);
    }


    /**
     * @param id         professor's id
     * @param university existing university
     * @return selected professor for university
     */
    public Optional<Professor> find(University university, Long id) {
        return professorRepository.findByIdAndUniversity(id, university);
    }


    public Optional<Professor> find(String universityname, Long id) {
        Optional<University> university = universityRepository.findById(universityname);
        if (university.isPresent()) {
            return professorRepository.findByIdAndUniversity(id, university.get());
        } else {
            return Optional.empty();
        }
    }

    /**
     * @return all available professors
     */
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public List<Professor> findAll(University university) {
        return professorRepository.findAllByUniversity(university);
    }
    /**
     * Creates new professor.
     *
     * @param professor new professor
     */
    @Transactional
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    /**
     * Updates existing professor.
     *
     * @param professor professor to be updated
     */
    @Transactional
    public void update(Professor professor) {
        professorRepository.save(professor);
    }

    /**
     * Deletes existing professor.
     *
     * @param employeeID existing employee's id to be deleted
     */
    @Transactional
    public void delete(int employeeID) {
        professorRepository.deleteById((long) employeeID);
    }




}
