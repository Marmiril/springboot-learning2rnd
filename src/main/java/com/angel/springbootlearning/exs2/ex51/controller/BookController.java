package com.angel.springbootlearning.exs2.ex51.controller;

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

import com.angel.springbootlearning.exs2.ex51.dto.BookDataResponse;
import com.angel.springbootlearning.exs2.ex51.dto.BookListResponse;
import com.angel.springbootlearning.exs2.ex51.dto.BookRequest;
import com.angel.springbootlearning.exs2.ex51.dto.BookResponse;
import com.angel.springbootlearning.exs2.ex51.dto.BookUpdate;
import com.angel.springbootlearning.exs2.ex51.mapper.BookMapper;
import com.angel.springbootlearning.exs2.ex51.model.Book;
import com.angel.springbootlearning.exs2.ex51.model.BookTheme;
import com.angel.springbootlearning.exs2.ex51.service.BookService;

@RestController 
@RequestMapping("/ex51/books")
public class BookController {

    private final BookService service;
    private final BookMapper mapper;

    public BookController(BookService service, BookMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping 
    public ResponseEntity<BookListResponse> getBooks(
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) BookTheme theme,
        @RequestParam(required = false) Integer year
    ) {
        List<Book> books = service.searchBooks(title, author, theme, year);

        List<BookDataResponse> responses = books.stream()
            .map(mapper::toResponse)
            .toList();

        // Check whether the repository containd any books
        boolean emptyRespository = service.getBooks().isEmpty();            

        // Determine whether the request contains search criteria
        boolean hasFilters = 
            title  != null  ||
            author != null  ||
            theme  != null  ||
            year   != null;



        // Select the response message based on the search result
        String message;
        if (emptyRespository) { message = "The book list is empty"; }
        else if (responses.isEmpty() && hasFilters) { message = "No books match the specific filters"; }
        else { message = "Books retrieved successfully"; }

        BookListResponse response = new BookListResponse(
            message,
            responses
        );    
        return ResponseEntity.ok(response);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable int id) {
        Book book = service.getBookById(id);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse(
            "Book retrieved successfully",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping 
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest request) {
        Book book = service.createBook(request);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse( 
            "Book created successfully",
            bookData
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<BookResponse> updateBook(
        @PathVariable int id,
        @RequestBody BookUpdate request 
    ) {
        Book book = service.updateBook(id, request);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse(
            "Book updated successfully!",
            bookData
        );
        return ResponseEntity.ok(response);

    }

    @PatchMapping ("/{id}")
    public ResponseEntity<BookResponse> patchBook(
        @PathVariable int id,
        @RequestBody BookUpdate request 
    ) {
        Book book = service.patchBook(id, request);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse(
            "Book updated successfully",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable int id) {
        Book book = service.deleteBookById(id);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse(
            "Book deleted successfully",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = { "title", "!author", "!theme", "!year"})
    public ResponseEntity<BookResponse> deleteBookByTitle(@RequestParam String title) {
        Book book = service.deleteBookByTitle(title);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse(
            "Book with title " + title + " deleted successfully",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"author", "!title", "!theme", "!year"})
    public ResponseEntity<BookListResponse> deleteBooksByAuthor(@RequestParam String author) {
        List<Book> books = service.deleteBooksByAuthor(author);
        List<BookDataResponse> bookData = books.stream()
            .map(mapper::toResponse)
            .toList();
        BookListResponse response = new BookListResponse(
            "Books by author: " + author + " deleted successfully!",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"theme", "!title", "!author","!year"})
    public ResponseEntity<BookListResponse> deleteBooksByTheme(@RequestParam BookTheme theme) {
        List<Book> books = service.deleteBooksByTheme(theme);
        List<BookDataResponse> bookData = books.stream()
            .map(mapper::toResponse)
            .toList();
        BookListResponse response = new BookListResponse(
            "Books with theme: " + theme + " deleted successfully!",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"year", "!title", "!author", "!theme"})
    public ResponseEntity<BookListResponse> deleteBooksByYear(@RequestParam int year) {
        List<Book> books = service.deleteBooksByYear(year);
        List<BookDataResponse> bookData = books.stream()
            .map(mapper::toResponse)
            .toList();
        BookListResponse response = new BookListResponse(
            "Books from year: " + year + " deleted successfully!",
            bookData
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(params = {"title", "author", "theme", "year"})
    public ResponseEntity<BookResponse> deleteBooksByDetails(
        @RequestParam String title,
        @RequestParam String author,
        @RequestParam BookTheme theme,
        @RequestParam int year
    ) {
        Book book = service.deleteBookByDetails(title, author, theme, year);
        BookDataResponse bookData = mapper.toResponse(book);
        BookResponse response = new BookResponse(
            "Book deleted successfully",
            bookData
        );
        return ResponseEntity.ok(response);
    }
}
