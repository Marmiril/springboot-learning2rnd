/*
 * Exercise 50 – CRUD with DTOs
 *
 * Purpose:
 * Convert creation and update requests into models,
 * and models into public response DTOs.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex50.mapper;

import org.springframework.stereotype.Component;

import com.angel.springbootlearning.exs2.ex50.dto.CarRequest50;
import com.angel.springbootlearning.exs2.ex50.dto.CarResponse50;
import com.angel.springbootlearning.exs2.ex50.dto.CarUpdate50;
import com.angel.springbootlearning.exs2.ex50.model.Car50;

@Component // Allow spring to manage mapper as constructor
public class CarMapper50 {
    
    public Car50 toModel(int id, CarRequest50 request) {
        return new Car50(
            id,
            request.brand(),
            request.model(),
            request.year(),
            request.price(),
            request.notes().trim()
        );
    }

    public Car50 toUpdateCar50(Car50 car, CarUpdate50 request) {
        return new Car50(
            car.id(),
            car.brand(),
            car.model(),
            car.year(),
            request.price() != null ? request.price() : car.price(),
            request.notes() != null ? request.notes().trim() : car.notes()
        );
    }

    public CarResponse50 toResponse(Car50 car) {
        return new CarResponse50(
            car.id(),
            car.brand(),
            car.model(),
            car.year()
        );
    }
}
