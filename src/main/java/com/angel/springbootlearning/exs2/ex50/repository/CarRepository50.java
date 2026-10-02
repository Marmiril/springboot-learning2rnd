/* * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Store and retrieve cars through an in-memory repository.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.angel.springbootlearning.exs2.ex50.model.Car50;

@Repository 
public class CarRepository50 {
    
    private final List<Car50> cars = new ArrayList<>();

    public List<Car50> findAll(){ return List.copyOf(cars); }

    public Optional<Car50> findById(int id) {
        return cars.stream()
            .filter(car -> car.id() == id)
            .findFirst();
    }

    public List<Car50> findByBrand(String brand) {
        return cars.stream()
            .filter(car -> car.brand().equalsIgnoreCase(brand.trim()))
            .toList();
    }

    public List<Car50> findByModel(String model) {
        return cars.stream()
            .filter(car -> car.model().equalsIgnoreCase(model.trim()))
            .toList();
    }

    public List<Car50> findByYear(int year) {
        return cars.stream()
            .filter(car -> car.year() == year)
            .toList();
    }

    public Optional<Car50> findByDetails(String brand, String model, int year) {
        return cars.stream()
            .filter(car -> car.brand().equalsIgnoreCase(brand.trim()))
            .filter(car -> car.model().equalsIgnoreCase(model.trim()))
            .filter(car -> car.year() == year)
            .findFirst();
    }

    public Car50 create(Car50 car) {
        cars.add(car);
        return car;
    }

    public Optional<Car50> update(Car50 updatedCar) {
        for (int index = 0; index < cars.size(); index++) {
            if (cars.get(index).id() == updatedCar.id()) {
                cars.set(index, updatedCar);
                return Optional.of(updatedCar);
            }
        }
        return Optional.empty();
    }

    public Optional<Car50> deleteById(int id) {
        Optional<Car50> car = findById(id);
        car.ifPresent(cars::remove);
        return car;
    }

    public List<Car50> deleteByBrand(String brand) {
        List<Car50> carsToRemove = findByBrand(brand);
        cars.removeAll(carsToRemove);
        return carsToRemove;
    }

    public List<Car50> deleteByModel(String model) {
        List<Car50> carsToRemove = findByModel(model);
        cars.removeAll(carsToRemove);
        return carsToRemove;
    }

    public List<Car50> deleteByYear(int year) {
        List<Car50> carsToDelete = findByYear(year);
        cars.removeAll(carsToDelete);
        return carsToDelete;
    }

    public Optional<Car50> deleteByDetails(String brand, String model, int year) {
        Optional<Car50> carToDelete = findByDetails(brand, model, year);
        carToDelete.ifPresent(cars::remove);
        return carToDelete;
    }

}
