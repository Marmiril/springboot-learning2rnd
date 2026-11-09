/*
 * Exercise 53 - Persistent REST CRUD
 * Purpose: Expose product operations through REST endpoints.
 * URLs: GET http://localhost:8080/ex53/products
 */


package com.angel.springbootlearning.persistence2.ex53.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angel.springbootlearning.persistence2.ex53.dto.ListProductResponse;
import com.angel.springbootlearning.persistence2.ex53.model.Product53;
import com.angel.springbootlearning.persistence2.ex53.service.ProductService;

@Controller 
@RequestMapping("/ex53/products")
public class ProductController {
    
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public ListProductResponse getAllProducts() { return service.getAllProducts(); }

    

}
