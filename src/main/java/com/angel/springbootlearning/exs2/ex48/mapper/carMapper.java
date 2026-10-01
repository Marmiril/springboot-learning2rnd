/*
 * Exercise 48 – Hide Internal Fields
 *
 * Purpose:
 * Map a car into a public DTO, excluding internal data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex48.mapper;

import com.angel.springbootlearning.exs2.ex48.dto.CarResponse48;
import com.angel.springbootlearning.exs2.ex48.model.Car48;

public class carMapper {

    public CarResponse48 toResponse(Car48 car) {
        return new CarResponse48(
            car.id(),
            car.brand(),
            car.model(),
            car.year()
        );
    }    
}
