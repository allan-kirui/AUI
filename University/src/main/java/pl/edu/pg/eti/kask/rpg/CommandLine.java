package pl.edu.pg.eti.kask.rpg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;
import pl.edu.pg.eti.kask.rpg.character.service.ProfessorService;
import pl.edu.pg.eti.kask.rpg.university.entity.University;
import pl.edu.pg.eti.kask.rpg.university.service.UniversityService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Component for interaction with university using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private ProfessorService professorService;

    private UniversityService universityService;

    @Autowired
    public CommandLine(ProfessorService professorService, UniversityService universityService) {
        this.professorService = professorService;
        this.universityService = universityService;
    }


    @Override
    public void run(String... args) throws Exception {
        professorService.findAll().forEach(System.out::println);
        int choice = 0;
        while (choice != 6) {
            System.out.println("Press 1 to Display all Professors:");
            System.out.println("Press 2 to Display all Universities:");
            System.out.println("Press 3 to Add professor to a University:");
            System.out.println("Press 4 to Delete an Professor:");
            System.out.println("Press 5 to Delete a University:");
            System.out.println("Press 6 to Exit");

            Scanner myObj = new Scanner(System.in);
            choice = myObj.nextInt();  // Read university input

            // DISPLAY ALL PROFESSORS
            if (choice == 1) {
                professorService.findAll().forEach(System.out::println);
            }
            // DISPLAY ALL UNIVERSITIES
            else if (choice == 2) {
                universityService.findAll().forEach(System.out::println);
            }
            // ADD professor TO A UNIVERSITY
            else if (choice == 3) {
                List<University> allUniversities = universityService.findAll();
                int counter = 0;
                for (University university : allUniversities) {
                    System.out.println(counter + " -- " + university.getName());
                    counter++;
                }
                System.out.println("Give Professor's workplace: ");
                int professorWorkplace = myObj.nextInt();
                myObj.nextLine();

                System.out.println("Give Professor's name: ");
                String professorName = myObj.nextLine();
                System.out.println("Give Professor's age: ");
                int professorAge = myObj.nextInt();
                myObj.nextLine();
                System.out.println("Give Professor's education: ");
                String professorEducation = myObj.nextLine();
                System.out.println("Give Professor's experience: ");
                int professorExperience = myObj.nextInt();
                myObj.nextLine();

                Professor newProfessor = Professor.builder()
                        .name(professorName)
                        .age(professorAge)
                        .education(professorEducation)
                        .experience(professorExperience)
                        .university(allUniversities.get(professorWorkplace))
                        .build();

                professorService.create(newProfessor);
            } // DELETING A SPECIFIC professor
            else if (choice == 4) {
                List<Professor> allProfessors = professorService.findAll();
                for (Professor professor : allProfessors) {
                    System.out.println(professor.getId() + " -- " + professor.getName());
                }
                int chosenOne = myObj.nextInt();
                professorService.delete(chosenOne);

            }// DELETE A SPECIFIC UNIVERSITY
            else if (choice == 5) {
                List<University> allUniversities = universityService.findAll();
                int counter = 0;
                for (University university : allUniversities) {
                    System.out.println(counter + " -- " + university.getName());
                    counter++;
                }
                System.out.println("Pick university number to delete: ");
                int chosenUniversity = myObj.nextInt();
                myObj.nextLine();
                universityService.delete(allUniversities.get(chosenUniversity).getName());
                List<Professor> allProfessors = professorService.findAll();
                for (Professor professor : allProfessors) {
                    if (Objects.equals(professor.getUniversity().getName(), allUniversities.get(chosenUniversity).getName())) {
                        professorService.delete(Math.toIntExact(professor.getId()));
                    }
                }
            }

        }
    }

}
