/*
 * Exercise 40 - Architecture Review
 *
 * Purpose:
 * Exposes the HTTP endpoints for managing students
 * while delegating business logic to StudentService40.
 *
 * URLs:
 * http://localhost:8080/exercise40/students
 * http://localhost:8080/exercise40/students/{id}
 * http://localhost:8080/exercise40/students?name={name}
 * http://localhost:8080/exercise40/students?role={role}
 */

package com.angel.springbootlearning.exercises.exercise40.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.exercises.exercise40.dto.StudentDeletionResponse40;
import com.angel.springbootlearning.exercises.exercise40.dto.StudentListResponse40;
import com.angel.springbootlearning.exercises.exercise40.dto.StudentRequest40;
import com.angel.springbootlearning.exercises.exercise40.dto.StudentResponse40;
import com.angel.springbootlearning.exercises.exercise40.exception.InvalidStudentRequestException40;
import com.angel.springbootlearning.exercises.exercise40.model.Student40;
import com.angel.springbootlearning.exercises.exercise40.service.StudentService40;

@RestController
@RequestMapping("/exercise40/students")
public class StudentController40 {

    private final StudentService40 studentService;

    public StudentController40(StudentService40 studentService) {
        this.studentService = studentService;
    }

    @GetMapping(params = {"!name", "!role"})
    public ResponseEntity<StudentListResponse40> getStudents() {
        List<Student40> students = studentService.getStudents();

        String message = students.isEmpty()
            ? "There are no students registered yet"
            : "Student retrieved successfully";
        
        StudentListResponse40 response = new StudentListResponse40(
            message,
            students
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse40> getStudentById(@PathVariable int id) {
        Student40 student = studentService.getStudentById(id);
        StudentResponse40 response = new StudentResponse40(
            "Student with id: " + id + " retrieved successfully",
            student
        );

        return ResponseEntity.ok(response);
    }

    /*
     * The negative role condition prevents this mapping from accepting
     * name and role simultaneously.
     */
    @GetMapping(params = {"name", "!role"})
    public ResponseEntity<StudentResponse40> getStudentByName(@RequestParam String name) {
        Student40 student = studentService.getStudentByName(name);
        StudentResponse40 response = new StudentResponse40(
            "Student with name:" + name + " retrieved successfully.",
            student
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"role", "!name"})
    public ResponseEntity<StudentListResponse40> getStudentsByRole(@RequestParam String role) {
        List<Student40> students = studentService.getStudentByRole(role);
        StudentListResponse40 response = new StudentListResponse40(
            "Students with role: " + role + " retrieved successfully.",
            students
        );
        return ResponseEntity.ok(response);
    }

    /*
     * This explicit mapping prevents Spring from silently choosing
     * one filter when both filters are present.
     */
    @GetMapping(params = {"name", "role"})
    public void rejectCombinedGetFilters() { throw new InvalidStudentRequestException40("Name and role filters cannot be used simultaneously"); }

    @PostMapping
    public ResponseEntity<StudentResponse40> createStudent(@RequestBody(required = false) StudentRequest40 request) {
        Student40 createdStudent = studentService.createStudent(request);
        StudentResponse40 response = new StudentResponse40(
            "Student created succesfully",
            createdStudent
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse40> updateStudent(
        @PathVariable int id,
        @RequestBody StudentRequest40 request) {
            Student40 updatedStudent = studentService.updateStudent(id, request);

            StudentResponse40 response = new StudentResponse40(
                "Student with id: " + id + " updated successfully",
                updatedStudent
            );

            return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponse40> patchStudent(
        @PathVariable int id,
        @RequestBody StudentRequest40 request) {
            Student40 patchedStudent = studentService.patchStudent40(id, request);
            StudentResponse40 response = new StudentResponse40(
                "Student with id: " + id + " partially updated successfully",
                patchedStudent
            );

            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse40> deleteStudentById(@PathVariable int id) {
        Student40 deletedStudent = studentService.deleteStudentById(id);
        StudentResponse40 response = new StudentResponse40(
            "Student with id: " + id + " deleted successfully",
            deletedStudent
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"name", "!role"})
    public ResponseEntity<StudentResponse40> deleteStudentByName(@RequestParam String name) {
        Student40 deletedStudent = studentService.deleteStudentByName(name);
        StudentResponse40 response = new StudentResponse40(
            "Student with name: " + name + " deleted successfully",
            deletedStudent
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"role", "!name"})
    public ResponseEntity<StudentDeletionResponse40> deleteStudentsByRole(@RequestParam String role) {
        List<Student40> deletedStudents = studentService.deleteStudentsByRole(role);
        StudentDeletionResponse40 response = new StudentDeletionResponse40(
            "Students with role: " + role + " role deleted successfully",
            deletedStudents,
            deletedStudents.size()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"name", "role"})
    public void rejectedCombineDeleteFilters() { throw new InvalidStudentRequestException40("Name and role filters cannot be used simultaneously!"); }

}


