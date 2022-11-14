package pl.edu.pg.student.universities.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.universities.university.entity.University;

public interface UniversityRepository extends JpaRepository<University, String> {
}
