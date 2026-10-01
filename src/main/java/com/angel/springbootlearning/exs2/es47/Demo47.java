/*
 * Exercise 47 – Separate Mapper
 *
 * Purpose:
 * Map a student into a DTO and wrap it with a message.
 *
 * URLs:
 * None.
 */
package com.angel.springbootlearning.exs2.es47;

import java.time.LocalDateTime;

import com.angel.springbootlearning.exs2.es47.dto.Res47;
import com.angel.springbootlearning.exs2.es47.dto.StuRes47;
import com.angel.springbootlearning.exs2.es47.mapper.StudentMapper;
import com.angel.springbootlearning.exs2.es47.model.Stud47;

public class Demo47 {

    public static void main(String[] args) {
        Stud47 student = new Stud47(
            1,
            "Ángel",
            "Developer",
            LocalDateTime.now()
        );

        StudentMapper mapper = new StudentMapper();
        StuRes47 mappedStudent = mapper.toResponse(student);

        Res47 response = new Res47(
            "Student retrieved successfully",
            mappedStudent
        );

        System.out.println("Internal mode: " + student);
        System.out.println("Response: " + response);
    }
    
}
