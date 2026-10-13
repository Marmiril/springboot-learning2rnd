package com.angel.springbootlearning.exs2.ex51.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.angel.springbootlearning.exs2.ex51.model.Book;
import com.angel.springbootlearning.exs2.ex51.model.BookTheme;

@Repository 
public class BookRepository {
    
    private final List<Book> books = new ArrayList<>();
    
    public List<Book> findAll() { return List.copyOf(books); }

    public Optional<Book> findById(int id) { return books.stream().filter(book -> book.id() == id ).findFirst(); }

    public Optional<Book> findByTitle(String title) { return books.stream().filter(book -> book.title().equalsIgnoreCase(title)).findFirst(); }

    public List<Book> findByAuthor(String author) { return books.stream().filter(book -> book.author().equalsIgnoreCase(author)).toList(); }

    public List<Book> findByTheme(BookTheme theme) { return books.stream().filter(book -> book.theme() == theme).toList(); }

    public List<Book> findByYear(int year) { return books.stream().filter(book -> book.year() == year).toList(); }

}
