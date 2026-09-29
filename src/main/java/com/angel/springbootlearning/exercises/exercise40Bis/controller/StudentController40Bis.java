package com.angel.springbootlearning.exercises.exercise40Bis.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.exercises.exercise09.StudentResponseEntityController;
import com.angel.springbootlearning.exercises.exercise40Bis.dto.StudentListResponse40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.dto.StudentResponse40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.model.Student40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.service.StudentService40Bis;

@RestController 
@RequestMapping("/exercise40Bis/students")
public class StudentController40Bis {
    
    private final StudentService40Bis studentService;

    public StudentController40Bis(StudentService40Bis studentService) { this.studentService = studentService; }
    
    @GetMapping(params = {"!name", "!role"})
    public ResponseEntity<StudentListResponse40Bis> getStudents() {
        List<Student40Bis> students = studentService.getStudents();

        String message = students.isEmpty()
            ? "There are no students registered yet"
            : "Students retrieved successfully";
        
        StudentListResponse40Bis response = new StudentListResponse40Bis(
            message,
            students
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("id")
    public ResponseEntity<StudentResponse40Bis> getStudentById(@PathVariable int id) {
        Student40Bis student = studentService.getStudentById(id);
        StudentResponse40Bis response = new StudentResponse40Bis(
            "Student with id: " + id + " retrieved successfully",
            student
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"name", "!role"})
    public ResponseEntity<StudentResponse40Bis> getStudentByName(@RequestParam String name) {
        Student40Bis student = studentService.getStudentByName(name);
        StudentResponse40Bis response = new StudentResponse40Bis(
            "Student with name: " + name + " retrieved successfully",
            student
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"!name", "role"})
    public ResponseEntity<StudentListResponse40Bis> getStudentsByRole(@RequestParam String role) {
        List<Student40Bis> students = studentService.getStudentsByRole(role);
        StudentListResponse40Bis response = new StudentListResponse40Bis(
            "Students with role: " + role + " retrieved successfully",
            students
        );
        return ResponseEntity.ok(response);


    }
}
