package com.angel.springbootlearning.exs2.ex51.service;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exs2.ex51.mapper.BookMapper;
import com.angel.springbootlearning.exs2.ex51.repository.BookRepository;

@Service 
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    
    
}
