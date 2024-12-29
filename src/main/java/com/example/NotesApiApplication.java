package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Notes API.
 * This is the entry point of the Spring Boot application.
 */
@SpringBootApplication
public class NotesApiApplication {

    /**
     * Main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(NotesApiApplication.class, args);
    }
}
