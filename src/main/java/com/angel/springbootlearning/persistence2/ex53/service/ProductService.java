package com.angel.springbootlearning.persistence2.ex53.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.persistence2.ex53.dto.ProductRequest;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductUpdate;
import com.angel.springbootlearning.persistence2.ex53.exception.ProductNotFound;
import com.angel.springbootlearning.persistence2.ex53.mapper.ProductMapper;
import com.angel.springbootlearning.persistence2.ex53.model.Product53;
import com.angel.springbootlearning.persistence2.ex53.repository.ProductRepository53;

@Service 
public class ProductService {
    private final ProductRepository53 repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository53 repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Product53> getAllProducts() { return repository.findAll(); }

    public Product53 getById(Long id) { return repository.findById(id).orElseThrow(() -> new ProductNotFound(id)); }

    public Product53 getByName(String name) { return repository.findByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFound(name)); }
    

    public Product53 createProduct(ProductRequest request) {
        Product53 product = mapper.toModel(request);
        return repository.save(product); }

    public Product53 updateProduct(Long id, ProductUpdate request) {
        Product53 product =getById(id);
        mapper.toUpdate(product, request);
        return repository.save(product);
    }

    public Product53 deleteById(Long id) {
        Product53 product = getById(id);
        repository.delete(product);
        return product;
    }
}
