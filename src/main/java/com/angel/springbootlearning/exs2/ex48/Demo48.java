/*
 * Exercise 48 – Hide Internal Fields
 *
 * Purpose:
 * Show the complete car model and a response without internal data.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex48;

import com.angel.springbootlearning.exs2.ex48.dto.CarResponse48;
import com.angel.springbootlearning.exs2.ex48.dto.Res48;
import com.angel.springbootlearning.exs2.ex48.mapper.carMapper;
import com.angel.springbootlearning.exs2.ex48.model.Car48;

public class Demo48 {
    
    public static void main(String[] args) {
        Car48 car = new Car48(
            1,
            "Ferrari",
            "Testarossa",
            1984,
            18500.0,
            "Purchased by Angel the developer"
        );

        carMapper mapper = new carMapper();
        CarResponse48 mappedCar = mapper.toResponse(car);

        Res48 response = new Res48(
            "Car retreived successfully",
            mappedCar
        );

        System.out.println("Internal model: " + car);
        System.out.println("Resposne: " + response);
    }
}
