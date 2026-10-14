package com.angel.springbootlearning.exs2.ex51.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exs2.ex51.dto.BookRequest;
import com.angel.springbootlearning.exs2.ex51.exception.BookNotFound;
import com.angel.springbootlearning.exs2.ex51.exception.InvalidBook;
import com.angel.springbootlearning.exs2.ex51.mapper.BookMapper;
import com.angel.springbootlearning.exs2.ex51.model.Book;
import com.angel.springbootlearning.exs2.ex51.model.BookTheme;
import com.angel.springbootlearning.exs2.ex51.repository.BookRepository;

@Service 
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;
    private int nextIdd = 1;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Book> getBooks() { return repository.findAll(); }

    public Book getBookById(int id) { return requireBookById(id); }

    public 


/////////////////////////////////////////////////////////////////////////////

    private void validateFields(String value, String field) { if (value == null || value.isBlank()) { throw new InvalidBook(field + " is required"); }}

    private void validateYear(int year) { 
    int currentYear = java.time.Year.now().getValue();
    if (year > currentYear) { throw new InvalidBook("No books from future are allowed"); }
}
    
    private void validatePrice(Double price) {
    if (price == null) { throw new InvalidBook("Price is required"); }
    if (!Double.isFinite(price) || price <= 0) { throw new InvalidBook("Price must be a finite number greater than zero!"); }
}

    private void validateTheme(BookTheme theme) { if (theme == null) { throw new InvalidBook("Theme is required!"); }}

    private void verifyUniqueTitle(String title) { if(repository.findByTitle(title.trim()).isPresent()) { throw new InvalidBook("Title already exists"); } }

    private void validateRequest(BookRequest request) {
    if(request == null) { throw new InvalidBook("Request is required..."); }
    validateFields(request.title(), "Title");
    validateFields(request.author(), "Author");
    validateTheme(request.theme());
    validateYear(request.year());
    validatePrice(request.price());
    validateFields(request.notes(), "Notes");
}

    private Book requireBookById(int id) { return repository.findById(id).orElseThrow(() ->new BookNotFound(id)); }
}
