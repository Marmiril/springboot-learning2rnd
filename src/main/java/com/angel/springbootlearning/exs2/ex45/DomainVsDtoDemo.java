/*
 * Exercise 45 - Domain Model vs DTO
 *
 * Purpose:
 * Demonstrates the difference between received data,
 * the internal student model and returned data.
 *
 * URLs:
 * None. Run the main method to see the console output.
 */
package com.angel.springbootlearning.exs2.ex45;

import java.time.LocalDateTime;

import com.angel.springbootlearning.exs2.ex41.Student41;
import com.angel.springbootlearning.exs2.ex42.CreateStudentRequest;
import com.angel.springbootlearning.exs2.ex43.StudentResponse;

public class DomainVsDtoDemo {

    public static void main(String[] args) {
        // The client provides only the creation fields
        CreateStudentRequest request = new CreateStudentRequest(
                "Ángel",
                "Developer");

        // The application adds the ID and registration date.
        Student41 student = new Student41(
                1,
                request.name(),
                request.role(),
                LocalDateTime.now());

        // The response includes only the fields chosen for the client
        StudentResponse response = new StudentResponse(
                student.id(),
                student.name(),
                student.role());

        System.out.println("Received data: " + request);
        System.out.println("Internal model: " + student);
        System.out.println("Returned data: " + response);
    }

}
