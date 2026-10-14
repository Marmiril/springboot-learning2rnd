package com.angel.springbootlearning.exs2.ex51.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exs2.ex51.exception.InvalidBook;
import com.angel.springbootlearning.exs2.ex51.mapper.BookMapper;
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

    public List<Book> getBooks 

/////////////////////////////////////////////////////////////////////////////

private void ValidateFields(String value, String field) { if (value == null || value.isBlank()) { throw new InvalidBook(field + " is required"); }}
    
}
