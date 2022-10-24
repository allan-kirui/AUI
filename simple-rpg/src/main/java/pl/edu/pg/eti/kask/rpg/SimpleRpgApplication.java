package pl.edu.pg.eti.kask.rpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class.
 */
@SpringBootApplication
public class SimpleRpgApplication {

    /**
     * Application main entry point. Starts Spring Boot application context.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleRpgApplication.class, args);

    }

}
