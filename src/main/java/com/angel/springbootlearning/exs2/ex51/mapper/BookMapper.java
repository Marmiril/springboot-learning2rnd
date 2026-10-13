package com.angel.springbootlearning.exs2.ex51.mapper;

import org.springframework.stereotype.Component;

import com.angel.springbootlearning.exs2.ex51.dto.BookDataResponse;
import com.angel.springbootlearning.exs2.ex51.dto.BookRequest;
import com.angel.springbootlearning.exs2.ex51.dto.BookUpdate;
import com.angel.springbootlearning.exs2.ex51.model.Book;
import com.angel.springbootlearning.exs2.ex51.model.BookTheme;

@Component 
public class BookMapper {
    
    public Book toModel(int id, BookRequest request) {
        return new Book(
            id, 
            request.title().trim(),
            request.author().trim(),
            request.theme(),
            request.year(),
            request.price(),
            request.notes()
        );
    }

    public Book toUpdateBook(Book book, BookUpdate request) {
        return new Book(
            book.id(),
            book.title(),
            book.author(),
            book.theme(),
            book.year(),
            request.price(),
            request.notes()
        );
    }

    public BookDataResponse toResponse(Book book) {
        return new BookDataResponse(
            book.id(),
            book.title(),
            book.author(),
            book.theme(),
            book.year()
        );
    }
}
