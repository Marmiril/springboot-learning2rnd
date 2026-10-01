/*
 * Exercise 49 – DTOs per Operation
 *
 * Purpose:
 * Demonstrate creation, update and response mapping with separate DTOs.
 *
 * URLs:
 * None.
 */

package com.angel.springbootlearning.exs2.ex49;

import com.angel.springbootlearning.exs2.ex49.dto.CarRequest49;
import com.angel.springbootlearning.exs2.ex49.dto.CarUpdate49;
import com.angel.springbootlearning.exs2.ex49.dto.Response49;
import com.angel.springbootlearning.exs2.ex49.mapper.CarMapper49;
import com.angel.springbootlearning.exs2.ex49.model.Car49;

public class Demo49 {
    
    public static void main(String[] args) {

        CarMapper49 mapper = new CarMapper49();

        CarRequest49 request = new CarRequest49(
            "Ferrari",
            "Testarossa",
            1984,
            18500.0,
            "Purchased at auction by super famous Angel"           
        );

        Car49 original = mapper.toModel(1, request);

        CarUpdate49 update = new CarUpdate49(
            20000.0,
            "Purchased price corrected"
        );

        Car49 updated = mapper.toUpdateModel(original, update);

        Response49 response = new Response49(
            "Car updated successfully",
            mapper.toResponse(updated)
        );

        System.out.println("Creation request: " + request);
        System.out.println("Origianl model: " + original);        
        System.out.println("Update request: " + update);      
        System.out.println("Updated model: " + updated);
        System.out.println("Public response: " + response);
    }
}
