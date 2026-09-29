package com.angel.springbootlearning.exercises.exercise40Bis.service;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exercises.exercise40Bis.dto.StudentRequest40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.exception.DuplicateStudentNameException40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.exception.InvalidStudentRequestException40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.exception.StudentNotFoundException40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.model.Student40Bis;
import com.angel.springbootlearning.exercises.exercise40Bis.repository.StudentRepository40Bis;

@Service 
public class StudentService40Bis {
    
    private final StudentRepository40Bis studentRepository;

    private int nextId = 1;

    public StudentService40Bis(StudentRepository40Bis studentRepository) { this.studentRepository = studentRepository; }
    
    public List<Student40Bis> getStudents() { return studentRepository.findAll(); }
    public Student40Bis getStudentById(int id) { return requireStudentById(id); }
    public Student40Bis getStudentByName(String name) { return requireStudentByName(name); }
    public List<Student40Bis> getStudentsByRole(String role) { return requireStudentsByRole(role); }
    public Student40Bis createStudent(StudentRequest40Bis request) {
        validateRequest(request);
        validateUniqueName(request.name(), 0);

        Student40Bis student = new Student40Bis(
            nextId++,
            request.name(),
            request.role(),
            LocalDateTime.now(ZoneId.of("Europe/Madrid"))
        );

        return studentRepository.create(student);
    }
    public Student40Bis updateStudent(int id, StudentRequest40Bis request) {
        Student40Bis student = requireStudentById(id);

        validateRequest(request);
        validateUniqueName(request.name(), id);

        Student40Bis updatedStudent = new Student40Bis(
            student.id(),
            request.name(),
            request.role(),
            student.registrationDate()
        );

        return studentRepository
            .update(updatedStudent)
            .orElseThrow(() -> studentNotFoundById(id)
        );
    }
    public Student40Bis patchStudent(int id, StudentRequest40Bis request)     {
        Student40Bis student = requireStudentById(id);

        validateRequest(request);

        String patchedName = student.name();
        String patchedRole = student.role();

        if (request.name() != null) {
            validateRequestField(request.name(), "Name");
            validateUniqueName(request.name(), student.id());
            patchedName = request.name().trim();
        }

        if (request.role() != null) {
            validateRequestField(request.role(), "Role");
            patchedRole = request.role();
        }
        
        Student40Bis patchedStudent = new Student40Bis(
            student.id(),
            patchedName,
            patchedRole,
            student.registrationDate()
        );

        return studentRepository 
            .update(patchedStudent)
            .orElseThrow(() -> studentNotFoundById(id));
    }
    public Student40Bis deleteStudentById(int id) {
        Student40Bis student = requireStudentById(id);
        studentRepository.deleteById(id);
        return student;
    }
    public Student40Bis deleteStudentByName(String name) {
        validateRequestField(name, "Name");
        Student40Bis student = requireStudentByName(name);
        studentRepository.deleteByName(name);
        return student;
    }
    public List<Student40Bis> deleteStudentsByRole(String role) {
        requireStudentsByRole(role);
        return studentRepository.deleteByRole(role);
    }
           
    ///////////////////////////////////////////////
    
    private Student40Bis requireStudentById(int id) {
        return studentRepository
            .findById(id)
            .orElseThrow(() -> studentNotFoundById(id));
    }
    private Student40Bis requireStudentByName(String name) {
        validateRequestField(name, "Name");
        return studentRepository
            .findByName(name)
            .orElseThrow(() -> new StudentNotFoundException40Bis(name));
    }
    private List<Student40Bis> requireStudentsByRole(String role) {
        validateRequestField(role, "Role");
        List<Student40Bis> students = studentRepository.findByRole(role);
        if (students.isEmpty()) { throw new StudentNotFoundException40Bis("There is no student with role: " + role); }
        return students;
    }

    private StudentNotFoundException40Bis studentNotFoundById(int id) { return new StudentNotFoundException40Bis("There is no student with such id: " + id); }
    
    private void validateUniqueName(String name, int currentId) {
        name = name.trim();

        boolean duplicated = studentRepository
            .findByName(name)
            .filter(student -> student.id() != currentId)
            .isPresent();
        if (duplicated) { throw new DuplicateStudentNameException40Bis("There is already a student with such name: " + name); }
    }
    private void validateRequestField(String value, String fieldName) {
        if (value == null || value.isBlank()) { throw new InvalidStudentRequestException40Bis(fieldName + " is required!"); }
    }
    private void validateRequest(StudentRequest40Bis request) {
        if (request == null) { throw new InvalidStudentRequestException40Bis("Request fields are empty..."); }
        validateRequestField(request.name(), "Name");
        validateRequestField(request.role(), "Role");
    }
}
