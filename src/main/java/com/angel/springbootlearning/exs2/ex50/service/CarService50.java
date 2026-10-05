/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Apply business rules and coordinate car repository operations.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.exs2.ex50.mapper.CarMapper50;
import com.angel.springbootlearning.exs2.ex50.model.Car50;
import com.angel.springbootlearning.exs2.ex50.repository.CarRepository50;

@Service 
public class CarService50 {
    
    private final CarRepository50 repository;
    private final CarMapper50 mapper;

    public CarService50(
        CarRepository50 repository,
        CarMapper50 mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Car50> getCars() { return repository.findAll(); }
}
