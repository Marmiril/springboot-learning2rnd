/*
 * Exercise 53 - Persistent REST CRUD
 * Purpose: Expose product operations through REST endpoints.
 * URLs: GET http://localhost:8080/ex53/products
 */


package com.angel.springbootlearning.persistence2.ex53.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import com.angel.springbootlearning.persistence2.ex53.dto.ListProductResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductRequest;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductUpdate;
import com.angel.springbootlearning.persistence2.ex53.service.ProductService;

@RestController 
@RequestMapping("/ex53/products")
public class ProductController {
    
    private final ProductService service;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping                      public ResponseEntity<ListProductResponse> getAllProducts() { return ResponseEntity.ok(service.getAllProducts()); }

    @GetMapping("/{id}")             public ResponseEntity<ProductResponse>     getProductById(@PathVariable Long id) { return ResponseEntity.ok(service.getProductById(id)); }

    @GetMapping("/by-name/{name}")   public ResponseEntity<ProductResponse>     getProductByName(@PathVariable String name) { return ResponseEntity.ok(service.getByName(name)); }

    @GetMapping("/by-stock/{stock}") public ResponseEntity<ListProductResponse> getProductsByStock(@PathVariable Integer stock) { return ResponseEntity.ok(service.getByStock(stock)); }

    @GetMapping("/by-price/{price}") public ResponseEntity<ListProductResponse> getProductsByPrice(@PathVariable BigDecimal price) {return ResponseEntity.ok(service.getByPrice(price)); }

    @GetMapping("/price-range")      public ResponseEntity<ListProductResponse> getProductsByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) { return ResponseEntity.ok(service.getByPriceBetween(minPrice, maxPrice)); }

    @PostMapping                     public ResponseEntity<ProductResponse>     createProduct(@RequestBody ProductRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body( service.createProduct(request)); }

    @PutMapping("/{id}")             public ResponseEntity<ProductResponse>     updateProduct(@PathVariable Long id, @RequestBody ProductUpdate request) { return ResponseEntity.ok(service.updateProduct(id, request)); }

    @PatchMapping("/{id}")           public ResponseEntity<ProductResponse> patchProduct(@PathVariable Long id, ProductUpdate request) { return ResponseEntity.ok(service.patchProduct(id, request)); }

    @DeleteMapping("/{id}")          public ResponseEntity<ProductResponse>     deleteProductById(@PathVariable Long id) { return ResponseEntity.ok(service.deleteById(id)); }

    @DeleteMapping("by-name/{name}") public ResponseEntity<ProductResponse>     deleteProductByName(@PathVariable String name) { return ResponseEntity.ok(service.deleteByName(name)); }
}
