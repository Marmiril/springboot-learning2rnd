package com.angel.springbootlearning.persistence2.ex53.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.persistence2.ex53.dto.ListProductResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductDataResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductRequest;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductUpdate;
import com.angel.springbootlearning.persistence2.ex53.exception.InvalidProduct;
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

    public ProductDataResponse getProductResponse(Long id) { Product53 product = getById(id); return mapper.toResponse(product); }

    public Product53 getById(Long id) { return repository.findById(id).orElseThrow(() -> new ProductNotFound(id)); }

    public ProductResponse getProductById(Long id) {
        Product53 product = getById(id);
        return new ProductResponse(
            mapper.toResponse(product),
            "Product found by id: " + id
        );
    }

    public ProductResponse getByName(String name) { 
        Product53 product = repository.findByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFound(name));        
        return new ProductResponse(
            mapper.toResponse(product),
            "Product found by name: " + name
        );
    }

    public ListProductResponse getByStock(Integer stock) {
        List<Product53> products = repository.findByStock(stock);
        if(products.isEmpty()) { throw new ProductNotFound(stock); }
        List<ProductResponse> responses = products.stream()
        .map(product -> new ProductResponse(
            mapper.toResponse(product),
            "Product found successfully"
        ))
        .toList();
        return new ListProductResponse(responses);
    }

    public Product53 createProduct(ProductRequest request) {
        Product53 product = mapper.toModel(request);
        validateCreateRequest(request);
        return repository.save(product); }

    public Product53 updateProduct(Long id, ProductUpdate request) {
        Product53 product =getById(id);
        validateUpdateRequest(request);
        mapper.toUpdate(product, request);
        return repository.save(product);
    }

    public Product53 deleteById(Long id) {
        Product53 product = getById(id);
        repository.delete(product);
        return product;
    }
/* 
    private void validateFields(String value, String field) {
        if (value == null || value.isBlank()) { throw new InvalidProduct(field + " is required"); }
    }

    private void validatePrice(BigDecimal price) { 
        if (price == null || price.signum() < 0) { throw new InvalidProduct("Price cannot be negative or null"); }
    }

    private void validateStock(Integer stock) { if (stock == null || stock < 0) { throw new InvalidProduct("Stock cannot be negative"); } }
*/
    private void validateUpdateRequest(ProductUpdate request) {
        if (request == null) { throw new InvalidProduct("Request cannot be empty"); }
        validateFields(request.name(), "Name");
        validateUniqueName(request.name());
    }

    private void validateCreateRequest(ProductRequest request) {
        if (request == null) { throw new InvalidProduct("Request cannot be empty"); }
        validateFields(request.name(), "Name");
    }

    private void validateFields(Object value, String field) {
        if (value == null) { throw new InvalidProduct(field + " is required"); }
        
        switch (field) {
            case "name" -> { if(!(value instanceof String name) || name.isBlank()) { throw new InvalidProduct("Invalid product name"); } }
            case "price" -> { if(!(value instanceof BigDecimal price) || price.signum() <= 0) { throw new InvalidProduct("Price cannot be negative or null"); } }
            case "stock" -> { if(!(value instanceof Integer stock) || stock < 0) { throw new InvalidProduct("Stock cannot be negative"); } }
        }
    }

    private void validateUniqueName(String name) {
        if (repository.findByNameIgnoreCase(name.trim()).isPresent()) { throw new InvalidProduct("Product already exists"); }
    }

    private void validateRequest(Object request) {
        if (request == null) { throw new InvalidProduct("Request cannot be empty"); }
        if (request instanceof ProductRequest create) { 
            validateFields(create.name(),"name");
            validateFields(create.price(),"price");
            validateFields(create.stock(),"stock");
        }
        if (request instanceof ProductUpdate update) {
            validateFields(update.name(), "name");
            validateUniqueName(update.name());
            validateFields(update.price(), "price");
            validateFields(update.stock(), "stock")
        }
    } 

}
