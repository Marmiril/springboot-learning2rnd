/*
 * Exercise 52 - H2 Database Introduction
 * Purpose: Run persistence exercises independently from previous controllers.
 * URLs:
 * http://localhost:8080/h2-console
 */

package com.angel.springbootlearning.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class PersistenceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PersistenceApplication.class, args);
    }
}
