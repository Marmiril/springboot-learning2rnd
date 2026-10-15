package com.angel.springbootlearning.exs2.ex51.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exs2.ex51.dto.BookRequest;
import com.angel.springbootlearning.exs2.ex51.dto.BookUpdate;
import com.angel.springbootlearning.exs2.ex51.exception.BookNotFound;
import com.angel.springbootlearning.exs2.ex51.exception.DuplicateBook;
import com.angel.springbootlearning.exs2.ex51.exception.InvalidBook;
import com.angel.springbootlearning.exs2.ex51.mapper.BookMapper;
import com.angel.springbootlearning.exs2.ex51.model.Book;
import com.angel.springbootlearning.exs2.ex51.model.BookTheme;
import com.angel.springbootlearning.exs2.ex51.repository.BookRepository;

@Service 
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;
    private int nextId = 1;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Book> getBooks() { return repository.findAll(); }

    public Book getBookById(int id) { return requireBookById(id); }

    public Book getByTitle(String title) {
        validateFields(title, "Title");
        return repository.findByTitle(title).orElseThrow(() -> new BookNotFound("There is no book with title: " + title));
    }

    public List<Book> getBooksByAuthor(String author) {
        validateFields(author, "Author");
        List<Book> books = repository.findByAuthor(author);
        if(books.isEmpty()) { throw new BookNotFound("There are no books in the list written by: " + author); }
        return books;
    }

    public List<Book> getBooksByTheme(BookTheme theme) {
        validateTheme(theme);
        List<Book> books = repository.findByTheme(theme);
        if(books.isEmpty()) { throw new BookNotFound("There are no books with theme: " + theme); }
        return books;
    }

    public List<Book> getBooksByYear(int year) {
        validateYear(year);
        List<Book> books = repository.findByYear(year);
        if (books.isEmpty()) { throw new BookNotFound("There are no books from year " + year); }
        return books;
    }

    public Book getBookByDetails(String title, String author, BookTheme theme, int year) {
        validateFields(title, "Title");
        validateFields(author, "Author");
        validateTheme(theme);
        validateYear(year);

        return repository.findByDetails(title, author, theme, year)
            .orElseThrow(() -> new BookNotFound(
                "There is no book with title: " + title.trim()
                + ", author: " + author.trim()
                + ", of " + theme
                + " from " + year)
            );
    }

    public Book createBook(BookRequest request) {
        validateRequest(request);
        Book book = mapper.toModel(nextId++, request);
        return repository.create(book);
    }

    public Book updateBook(int id, BookUpdate request) {
        Book book = getBookById(id);
        if (request == null) { throw new InvalidBook("Book update request is required!"); }
        validatePrice(request.price());
        validateFields(request.notes(), "Notes");
        Book updatedBook = mapper.toUpdateBook(book, request);
        return repository.update(updatedBook).orElseThrow(() -> new BookNotFound(id));
    }

    public Book patchBook(int id, BookUpdate request) {
        Book book = getBookById(id);
        if (request == null) { throw new InvalidBook("Request is required..."); }
        if (request.price() == null && request.notes() == null) { throw new InvalidBook("At least one field is required..."); }
        if (request.price() != null) { validatePrice(request.price()); }
        if (request.notes() != null) { validateFields(request.notes(), "Notes"); }
        Book patchedBook = mapper.toUpdateBook(book, request);
        return repository.update(patchedBook).orElseThrow(() -> new BookNotFound(id));
    }

    public List<Book> searchBooks (String title, String author, BookTheme theme, Integer year) {
        if (title != null) { validateFields(title, "Title");}
        if (author != null) { validateFields(author, "Author"); }
        if (theme != null ) { validateTheme (theme); }
        if (year != null) { validateYear(year);}

        return repository.findByFilters(title, author, theme, year);            
    }

    public Book deleteBookById(int id) {
        return repository.deleteById(id).orElseThrow(() -> new BookNotFound(id));
    }

    public Book deleteBookByTitle(String title) {
        validateFields(title, "Title");
        Book bookToDelete = repository.deleteByTitle(title).orElseThrow(() -> new BookNotFound("There is no book with title: " + title));
        return bookToDelete;

    }

    public List<Book> deleteBooksByAuthor(String author) {
        validateFields(author, "Author");
        List<Book> booksToDelete = repository.deleteByAuthor(author);
        if (booksToDelete.isEmpty()) { throw new BookNotFound("There are no books by author: " + author); }
        return booksToDelete;      
    }

    public List<Book> deleteBooksByTheme(BookTheme theme) {
        validateTheme(theme);
        List<Book> booksToDelete = repository.deleteByTheme(theme);
        if (booksToDelete.isEmpty()) { throw new BookNotFound("There are no books with theme: " + theme); }
        return booksToDelete;
    }

    public List<Book> deleteBooksByYear(int year) {
        validateYear(year);
        List<Book> booksToDelete = repository.deleteByYear(year);
        if (booksToDelete.isEmpty()) { throw new BookNotFound("There are no books from: " + year); }
        return booksToDelete;
    }

    public Book deleteBookByDetails(String title, String author, BookTheme theme, int year) {
        validateFields(title, "Title");
        validateFields(author, "Author");
        validateTheme(theme);
        validateYear(year);
        return repository.deleteByDetails(title, author, theme, year).orElseThrow(() -> new BookNotFound("There is no books with such details"));
    }


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

    private void verifyUniqueTitle(String title) { if(repository.findByTitle(title.trim()).isPresent()) { throw new DuplicateBook("Title already exists"); } }

    private void validateRequest(BookRequest request) {
    if(request == null) { throw new InvalidBook("Request is required..."); }
    validateFields(request.title(), "Title");
    verifyUniqueTitle(request.title());
    validateFields(request.author(), "Author");
    validateTheme(request.theme());
    validateYear(request.year());
    validatePrice(request.price());
    validateFields(request.notes(), "Notes");
}

    private Book requireBookById(int id) { return repository.findById(id).orElseThrow(() ->new BookNotFound(id)); }
}
