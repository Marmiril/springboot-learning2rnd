/*
 * Exercise 18 - Common HTTP status codes
 *
 * Purpose:
 * This exercise consolidates common HTTP status codes used in REST APIs:
 * 200 OK, 201 Created, 204 No Content, 400 Bad Request, 404 Not Found
 * and 409 Conflict.
 *
 * URL:
 * http://localhost:8080/exercise18/students
 */

package com.angel.springbootlearning.exercises.exercise18;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/exercise18/students")
@RestController
public class StudentHttpStatusController {

    // Will return all simulatees students with HTTP 200 OK
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents() { return new ResponseEntity<>(getExistingStudents(), HttpStatus.OK); }

    // Searchs for one student by id and returns 200 OK or 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id)  {
        StudentResponse student = findStudentById(id);
        if (student == null) { return notFound("Student with id " + id + " was not found!"); }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Creates a sutdent and returns 201 Created, 400 Bad Requiest or 409 Conflict
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody CreateStudentRequest request) {
        if (isBlank(request.name())) { return badRequest("Student name is mandatory!"); }
        if (isBlank(request.role())) { return badRequest("Student role must exist!"); }
        if (studentNameExists(request.name())) { return conflict("Student name already exists..."); }

        StudentResponse response = new StudentResponse(
            4,
            request.name(),
            request.role()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Simulates deleting a sutdent and returns 204 No Content or 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable int id) {
        if(findStudentById(id) == null) { return notFound("Student with id " + id + " was not found..."); }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Simulates existing stored students
    private List<StudentResponse> getExistingStudents() {
        return List.of(
            new StudentResponse(1, "Angel", "backend"),
            new StudentResponse(2, "Angello", "frontend"),
            new StudentResponse(3, "Angelloti", "fullstack")
        );
    }

    // Searches for a student by id
    private StudentResponse findStudentById(int id) {
        return getExistingStudents().stream()
            .filter(student -> student.id() == id)
            .findFirst()
            .orElse(null);
    }

    // Checks value of text if null, empty or only contains space
    private boolean isBlank(String value) { return value == null || value.isBlank(); }

    // Checks if the received student name already exists
    private boolean studentNameExists(String name) {
        return getExistingStudents().stream()
            .anyMatch(student -> student.name().equalsIgnoreCase(name));
    }

    // The reusable structure for bad request
    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    // Not found
    private ResponseEntity<ErrorResponse> notFound(String message) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    // Conflict
    private ResponseEntity<ErrorResponse> conflict(String message) {
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    public record CreateStudentRequest(String name, String role) {}

    public record StudentResponse(int id, String name, String role) {}    

    public record ErrorResponse(int status, String error, String message) {}
}
/*
200 - OK------
Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise18/students" `
  -Method Get
*/

/*
200 - OK------
Invoke-RestMethod `
  -Uri "http://localhost:8080/exercise18/students/1" `
  -Method Get
*/

/*
404 - NOT FOUND------
try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise18/students/99" `
      -Method Get `
      -UseBasicParsing
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
*/

/*
201 - CREATED-----
>> name = "Angie"
>> role = "fullstack"
>> } | ConvertTo-Json
>>
>> Invoke-RestMethod `
>> -Uri "http://localhost:8080/exercise18/students" `
>> -Method Post `
>> -ContentType "application/json" `
>> -Body $body
*/

/*
400 - BAD_REQUEST
$body = @{
    name = ""
    role = "backend"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise18/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body `
      -UseBasicParsing
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
*/

/*
409 - CONFLICT
$body = @{
    name = "Angel"
    role = "backend"
} | ConvertTo-Json

try {
    Invoke-WebRequest `
      -Uri "http://localhost:8080/exercise18/students" `
      -Method Post `
      -ContentType "application/json" `
      -Body $body `
      -UseBasicParsing
}
catch {
    $reader = New-Object System.IO.StreamReader($_.Exception.Response.GetResponseStream())
    $reader.ReadToEnd()
}
*/

/*
204 - NO CONTENT
Invoke-WebRequest `
  -Uri "http://localhost:8080/exercise18/students/1" `
  -Method Delete `
  -UseBasicParsing
*/