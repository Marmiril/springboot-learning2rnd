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

import com.angel.springbootlearning.exs2.ex50.dto.CarRequest50;
import com.angel.springbootlearning.exs2.ex50.dto.CarUpdate50;
import com.angel.springbootlearning.exs2.ex50.exception.CarNotFound50;
import com.angel.springbootlearning.exs2.ex50.exception.DuplicateCar50;
import com.angel.springbootlearning.exs2.ex50.exception.InvalidCar50;
import com.angel.springbootlearning.exs2.ex50.mapper.CarMapper50;
import com.angel.springbootlearning.exs2.ex50.model.Car50;
import com.angel.springbootlearning.exs2.ex50.repository.CarRepository50;

@Service 
public class CarService50 {
    
    private final CarRepository50 repository;
    private final CarMapper50 mapper;
    private int nextId = 1;

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

    public Car50 createCar(CarRequest50 request) {
        validateRequest(request);
        validateUniqueCar(
            request.brand(),
            request.model(),
            request.year()
        );

        Car50 car = mapper.toModel(nextId++, request);
        return repository.create(car);
    }

    public Car50 updateCar(int id, CarUpdate50 request) {
        Car50 car = getCarById(id);

        if (request == null) { throw new InvalidCar50 ("Car update request is required"); }

        validateFields(request.notes(), "Notes");
        validatePrice(request.price());

        Car50 updatedCar = mapper.toUpdateCar50(car, request);
        return repository.update(updatedCar)
            .orElseThrow(() -> new CarNotFound50("There is no car with id: " + id));
    }
    
    public Car50 patchCar(int id, CarUpdate50 request) {
        Car50 car = getCarById(id);

        if (request == null) { throw new InvalidCar50("Car patch request is required!");}

        double patchedPrice = car.price();
        String patchedNotes = car.notes();

        if (request.price() != null) {
            validatePrice(request.price());
            patchedPrice = request.price();
        }

        if (request.notes() != null) {
            validateFields(request.notes(), "Notes");
            patchedNotes = request.notes();
        }

        Car50 patchedCar = mapper.toUpdateCar50(car, request);
        return repository.update(patchedCar)
            .orElseThrow(() -> new CarNotFound50("There is no car with id: " + id));
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

    private void validatePrice(Double price) {
        if (price == null) { throw new InvalidCar50("Price is required"); }
        if (!Double.isFinite(price) || price <= 0) {
            throw new InvalidCar50("Price must be a finite number greater than zero!");
        }
    }

    private void validateRequest(CarRequest50 request) {
        if (request == null) { throw new InvalidCar50("Car request is required..."); }

        validateFields(request.brand(), "Brand");
        validateFields(request.model(), "Model");
        validateYear(request.year());
        validatePrice(request.price());
        validateFields(request.notes(), "Notes");
    }

    private void validateUniqueCar(String brand, String model, int year) {
        if (repository.findByDetails(brand, model, year).isPresent()) {
            throw new DuplicateCar50("There is a car with such details: " + brand.trim() +  (" - ") + model.trim() + (" - ") + year);
        }
    }

    private Car50 requireCarById(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new CarNotFound50("There is no car with id: " + id));
    }


}
