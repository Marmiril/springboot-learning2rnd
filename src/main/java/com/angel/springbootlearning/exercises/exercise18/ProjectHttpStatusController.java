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

@RequestMapping("/exercise18/projects")
@RestController
public class ProjectHttpStatusController {

    @GetMapping
    public ResponseEntity<List<?>> getProjects() {return new ResponseEntity<>(getExistingProjects(), HttpStatus.OK); }

    @GetMapping("/{id}") 
    public ResponseEntity<?>getProjectsById(@PathVariable int id) {
        ProjectResponse project = findProjectById(id);
        if (project == null) {return notFound("Project with id: " + id + " was not found!"); }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequest request) {

        if (isBlank(request.title())) { return badRequest ("Project must be titled"); }
        if (isBlank(request.type())) { return badRequest ("Projet must has a type!"); }
        if (projectTitleExist(request.title())) { return conflict ("There is already a project with such name " + request.title()); }

        ProjectResponse response = new ProjectResponse(
            4, 
            request.title(),
            request.type()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable int id) {
        if (findProjectById(id) == null) { return notFound("There is no project with such id " + id); }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean isBlank(String value) { return value == null || value.isBlank(); }

    private boolean projectTitleExist(String title) {
        return getExistingProjects().stream()
            .anyMatch(project -> project.title().equalsIgnoreCase(title));
    }

    private List<ProjectResponse> getExistingProjects() {
        return List.of(
            new ProjectResponse(1, "Pi...", "Math"),
            new ProjectResponse(2, "Vivaldi", "Music"),
            new ProjectResponse(3, "Karak-Urum", "Geography")
        );
    }

    private ProjectResponse findProjectById(int id) {
        return getExistingProjects().stream()
            .filter(project -> project.id() == id)
            .findFirst()
            .orElse(null);
    }

    private ResponseEntity<ErrorResponse> notFound(String message) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> badRequest(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    private ResponseEntity<ErrorResponse> conflict (String message) {
        HttpStatus status = HttpStatus.CONFLICT;
        
        ErrorResponse errorResponse = new ErrorResponse(
            status.value(),
            status.getReasonPhrase(),
            message
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    public record ProjectResponse(int id, String title, String type) {}
    public record CreateProjectRequest(String title, String type) {}
    public record ErrorResponse(int status, String error, String message) {}

    
}
