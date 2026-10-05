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

import com.angel.springbootlearning.exs2.ex50.exception.CarNotFound50;
import com.angel.springbootlearning.exs2.ex50.exception.InvalidCar50;
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

    public Car50 getCarById(int id) { return requireCarById(id); }

    public List<Car50> getCarsByBrand(String brand) {        
        validateFields(brand, "Brand");

        List<Car50> cars = repository.findByBrand(brand);

        if (cars.isEmpty()) { throw new CarNotFound50("There is no car with brand: " + brand); }
        
        return cars;
    }

    public List<Car50> getCarsByModel(String model)     {
        validateFields(model, "Model");
        List<Car50> cars = repository.findByModel(model);
        if(cars.isEmpty()) { throw new CarNotFound50("There is no car with model: " + model); }
        return cars;
    }

    public List<Car50> getCarsByYear(int year) {
        validateYear(year);
        List<Car50> cars = repository.findByYear(year);
        if (cars.isEmpty()) { throw new CarNotFound50("There is no cars from year: " + year); }
        return cars;
    }

    public Car50 getCarByDetalis(String brand, String model, int year) {
        validateFields(brand, "Brand");
        validateFields(model, "Model");
        validateYear(year);

    return repository.findByDetails(brand, model, year)
        .orElseThrow(() -> new CarNotFound50(
            "There is no car with brand: " + brand.trim()
                + ", model: " + model.trim()
                + ", year: " + year
        ));
    }

    ////////////////////////////////////////////////////////////////////////////////////////
/// 
    private void validateFields(String value, String field) {
        if (value == null || value.isBlank()) { throw new InvalidCar50(field + " is required");}
    }
    
    private void validateYear(int year) {
        int currentYear = java.time.Year.now().getValue();
        
        if (year < 1886 || year > currentYear) { throw new InvalidCar50 ("Year must be between 1886 and " + currentYear); }
    }

    private Car50 requireCarById(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new CarNotFound50("There is no car with id: " + id));
    }


}
