
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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angel.springbootlearning.exs2.ex50.dto.CarListResponse50;
import com.angel.springbootlearning.exs2.ex50.dto.CarRequest50;
import com.angel.springbootlearning.exs2.ex50.dto.CarResponse50;
import com.angel.springbootlearning.exs2.ex50.dto.CarUpdate50;
import com.angel.springbootlearning.exs2.ex50.dto.Response50;
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

    @GetMapping("/{id}")
    public CarResponse50 getCarById(@PathVariable int id) {
        Car50 car = service.getCarById(id);
        return mapper.toResponse(car);
    }

    @PostMapping 
    public ResponseEntity<Response50> createCar(@RequestBody CarRequest50 request) {
        Car50 car = service.createCar(request);
        CarResponse50 carResponse = mapper.toResponse(car);
        Response50 response = new Response50(
            "Car created successfully",
            carResponse
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Response50> updateCar(
        @PathVariable int id,
        @RequestBody CarUpdate50 request 
    ) {
        Car50 car = service.updateCar(id, request);
        CarResponse50 carResponse = mapper.toResponse(car);

        Response50 response = new Response50(
            "Car updated successfully",
            carResponse
        );
        return ResponseEntity.ok(response);
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<Response50> patchCar(
        @PathVariable int id,
        @RequestBody CarUpdate50 request
    ) {
        Car50 car = service.patchCar(id, request);
        CarResponse50 carResponse = mapper.toResponse(car);

        Response50 response = new Response50(
            "Car partially updated successfully",
            carResponse
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Response50> deleteById(@PathVariable int id) {
        Car50 car = service.deleteCarById(id);
        CarResponse50 carResponse = mapper.toResponse(car);
        Response50 response = new Response50(
            "Car with id: " + id + " deleted successfully",
            carResponse
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping (params = {"brand", "!model", "!year"})
    public ResponseEntity<CarListResponse50> deleteCarsByBrand(@RequestParam String brand) {
        List<Car50> cars = service.deleteCarsByBrand(brand);
        List<CarResponse50> responses = cars.stream()
            .map(mapper::toResponse)
            .toList();

        CarListResponse50 response = new CarListResponse50(
            "Cars deleted successfully!!",
            responses);
            
        return ResponseEntity.ok(response);
    }

    @DeleteMapping (params = { "model", "!brand","!year"})
    public ResponseEntity<CarListResponse50> deleteCarsByModel(@RequestParam String model) {
        List<Car50> cars = service.deleteCarsByModel(model);
        List<CarResponse50> responses = cars.stream()
            .map(mapper::toResponse)
            .toList();
        CarListResponse50 response = new CarListResponse50(
            "Cars deleted by model: " + model + "  successfully!",
            responses
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping (params = {"year", "!brand", "!model"})
    public ResponseEntity<CarListResponse50> deleteCarsByYear(@RequestParam int year) {
        List<Car50> cars = service.deleteCarByYear(year);
        List<CarResponse50> responses = cars.stream()
            .map(mapper::toResponse)
            .toList();
        CarListResponse50 response = new CarListResponse50(
            "Cars of year: " + year + " deleted successfully!!",
            responses
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping (params = {"brand", "model", "year"})
    public ResponseEntity<CarResponse50> deleteCarByDetails(
        @RequestParam String brand,
        @RequestParam String model,
        @RequestParam int year        
    ) {
        Car50 car = service.getCarByDetails(brand, model, year);
        CarResponse50 response = mapper.toResponse(car);
        return ResponseEntity.ok(response);
    }
    
}

