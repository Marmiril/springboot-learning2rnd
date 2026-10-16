package com.angel.springbootlearning.exs2.ex51.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        BookListResponse response = new BookListResponse(
            "Books retrieved successfully",
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
}
