
/*
 * Exercise 50 - CRUD with DTOs
 *
 * Purpose:
 * Expose car operations through REST endpoints.
 *
 * URLs:
 * http://localhost:8080/exercise50/cars
 * http://localhost:8080/exercise50/cars/{id}
 */


package com.angel.springbootlearning.exs2.ex50.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.exs2.ex50.dto.CarListResponse50;
import com.angel.springbootlearning.exs2.ex50.dto.CarResponse50;
import com.angel.springbootlearning.exs2.ex50.mapper.CarMapper50;
import com.angel.springbootlearning.exs2.ex50.model.Car50;
import com.angel.springbootlearning.exs2.ex50.service.CarService50;

@RestController 
@RequestMapping("/exercise50/cars")
public class CarController50 {
    
    private final CarService50 service;
    private final CarMapper50 mapper;

    public CarController50 (CarService50 service, CarMapper50 mapper) {
        this.service = service;
        this.mapper = mapper;
    }    

    @GetMapping 
    public CarListResponse50 getCars(
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) Integer year
    ) {
        List<Car50> cars = service.searchCars(brand, model, year);
        List<CarResponse50> responses = cars.stream()
            .map(mapper::toResponse)
            .toList();

        return new CarListResponse50("Cars retreived successfully!", responses);
    }
}
