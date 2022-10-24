package pl.edu.pg.eti.kask.rpg.datastore;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.entity.Subject;
import pl.edu.pg.eti.kask.rpg.serialization.CloningUtility;
import pl.edu.pg.eti.kask.rpg.university.entity.University;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For the sake of simplification instead of using real database this example is using an data source object which
 * should be put in servlet context in a single instance. In order to avoid
 * {@link java.util.ConcurrentModificationException} all methods are synchronized. Normally synchronization would be
 * carried on by the database server.
 */
@Log
@Component
public class DataStore {

    /**
     * Set of all available subject.
     */
    private Set<Subject> subject = new HashSet<>();

    /**
     * Set of all characters.
     */
    private Set<Professor> professors = new HashSet<>();

    /**
     * Set of all universities.
     */
    private Set<University> universities = new HashSet<>();

    /**
     * Seeks for all subject.
     *
     * @return list (can be empty) of all subject
     */
    public synchronized List<Subject> findAllSubjects() {
        return new ArrayList<>(subject);
    }

    /**
     * Seeks for the profession in the memory storage.
     *
     * @param name name of the profession
     * @return container (can be empty) with profession if present
     */
    public Optional<Subject> findSubject(String name) {
        return subject.stream()
                .filter(profession -> profession.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Stores new profession.
     *
     * @param profession new profession to be stored
     * @throws IllegalArgumentException if profession with provided name already exists
     */
    public synchronized void createSubject(Subject profession) throws IllegalArgumentException {
        findSubject(profession.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The profession name \"%s\" is not unique", profession.getName()));
                },
                () -> {
                   // profession.setId(findAllSubjects().stream().mapToLong(Subject::getId).max().orElse(0) + 1);
                    subject.add(profession);
                });
    }

    /**
     * Deletes existing character.
     *
     * @param name character's id
     * @throws IllegalArgumentException if character with provided id does not exist
     */
    public synchronized void deleteSubject(String name) throws IllegalArgumentException {
        findSubject(name).ifPresentOrElse(
                original -> subject.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The subject with name \"%s\" does not exist", name));
                });
    }


    /**
     * Seeks for all characters.
     *
     * @return list (can be empty) of all characters
     */
    public synchronized List<Professor> findAllProfessors() {
        return professors.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    /**
     * Seeks for single character.
     *
     * @param id character's id
     * @return container (can be empty) with character
     */
    public synchronized Optional<Professor> findProfessor(Long id) {
        return professors.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Stores new character.
     *
     * @param character new character
     */
    public synchronized void createProfessor(Professor character) throws IllegalArgumentException {
        character.setId(findAllProfessors().stream().mapToLong(Professor::getId).max().orElse(0) + 1);
        professors.add(character);
    }

    /**
     * Updates existing character.
     *
     * @param character character to be updated
     * @throws IllegalArgumentException if character with the same id does not exist
     */
    public synchronized void updateProfessor(Professor character) throws IllegalArgumentException {
        findProfessor(character.getId()).ifPresentOrElse(
                original -> {
                    professors.remove(original);
                    professors.add(character);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", character.getId()));
                });
    }

    /**
     * Deletes existing character.
     *
     * @param id character's id
     * @throws IllegalArgumentException if character with provided id does not exist
     */
    public synchronized void deleteProfessor(Long id) throws IllegalArgumentException {
        findProfessor(id).ifPresentOrElse(
                original -> professors.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", id));
                });
    }

    /**
     * Seeks for single university.
     *
     * @param name university's name
     * @return container (can be empty) with university
     */
    public synchronized Optional<University> findUniversity(String name) {
        return universities.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Seeks for single university using name and password. Can be use in authentication module.
     *
     * @param name     university's name
     * @param password university's password
     * @return container (can be empty) with university
     */
    public synchronized Optional<University> findUniversity(String name, String password) throws IllegalArgumentException {
        return universities.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Stores new university.
     *
     * @param university new university to be stored
     * @throws IllegalArgumentException if university with provided login already exists
     */
    public synchronized void createUniversity(University university) throws IllegalArgumentException {
        findUniversity(university.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The university name \"%s\" is not unique", university.getName()));
                },
                () -> {
                    //university.setId(findAllUniversities().stream().mapToLong(University::getId).max().orElse(0) + 1);
                    universities.add(university);
                });
    }

    /**
     * Get stream to be used (for filtering, sorting, etc) in repositories.
     *
     * @return character's stream
     */
    public Stream<Professor> getCharacterStream() {
        return professors.stream();
    }

    /**
     * Seeks for all universities.
     *
     * @return list (can be empty) of all universities
     */
    public synchronized List<University> findAllUniversities() {
        return universities.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

}
