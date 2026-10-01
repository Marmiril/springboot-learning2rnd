/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Map creation, update and response DTOs to and from the car model.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex49.mapper;

import com.angel.springbootlearning.exs2.ex49.dto.CarRequest49;
import com.angel.springbootlearning.exs2.ex49.dto.CarResponse49;
import com.angel.springbootlearning.exs2.ex49.dto.CarUpdate49;
import com.angel.springbootlearning.exs2.ex49.model.Car49;

public class CarMapper49 {
    
    public Car49 toModel(int id, CarRequest49 request) {
        return new Car49(
            id, 
            request.brand(),
            request.model(),
            request.year(),
            request.price(),
            request.notes()
        );
    }

    public Car49 toUpdateModel(Car49 car, CarUpdate49 request) {
        return new Car49(
            car.id(),
            car.brand(),
            car.model(),
            car.year(),
            request.price(),
            request.notes()
        );
    }

    public CarResponse49 toResponse(Car49 car) {
        return new CarResponse49(
            car.id(),
            car.brand(),
            car.model(),
            car.year()
        );
    }
}
