package com.angel.springbootlearning.persistence2.ex53.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.angel.springbootlearning.persistence2.ex53.dto.ListProductResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductDataResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductFields;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductRequest;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductUpdate;
import com.angel.springbootlearning.persistence2.ex53.exception.DuplicateProduct;
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
        validateFields(name, "name");        
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

    public ListProductResponse getByPrice(BigDecimal price) {
        List<Product53> products = repository.findByPrice(price);
        if(products.isEmpty()) { throw new ProductNotFound(price); }
        
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
        validateRequest(request);
        validateUniqueName(request.name(), null);
        return repository.save(product); }

    public Product53 updateProduct(Long id, ProductUpdate request) {
        validateRequest(request);
        validateUniqueName(request.name(), id);
        
        Product53 product =getById(id);
        mapper.toUpdate(product, request);
        return repository.save(product);
    }

    public Product53 deleteById(Long id) {
        Product53 product = getById(id);
        repository.delete(product);
        return product;
    }

    public Product53 deleteByName(String name) {
        validateFields(name, "name");
        Product53 product = repository.findByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFound(name));
        repository.delete(product);
        return product;
    }

    public ListProductResponse getByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        if(minPrice == null || maxPrice == null
                || minPrice.signum() < 0
                || maxPrice.compareTo(minPrice) < 0) {
            throw new InvalidProduct("Invalid price range"); }

        List<Product53> products = repository.findByPriceBetween(minPrice, maxPrice);
        List<ProductResponse> responses = products.stream()
                .map(product -> new ProductResponse(
                    mapper.toResponse(product),
                    "Product found successfully"
                ))
                .toList();
            return new ListProductResponse(responses);
    }

    ////////////////////////////////////////////////////////
    /// 
/* 
    private void validateFields(String value, String field) {
        if (value == null || value.isBlank()) { throw new InvalidProduct(field + " is required"); }
    }

    private void validatePrice(BigDecimal price) { 
        if (price == null || price.signum() < 0) { throw new InvalidProduct("Price cannot be negative or null"); }
    }

    private void validateStock(Integer stock) { if (stock == null || stock < 0) { throw new InvalidProduct("Stock cannot be negative"); } }

    private void validateUpdateRequest(ProductUpdate request) {
        if (request == null) { throw new InvalidProduct("Request cannot be empty"); }
        validateFields(request.name(), "Name");
        validateUniqueName(request.name());
    }

    private void validateCreateRequest(ProductRequest request) {
        if (request == null) { throw new InvalidProduct("Request cannot be empty"); }
        validateFields(request.name(), "Name");
    }
*/
    private void validateFields(Object value, String field) {
        if (value == null) { throw new InvalidProduct(field + " is required"); }
 
        switch (field) {
            case "name" -> { if(!(value instanceof String name) || name.isBlank()) { throw new InvalidProduct("Invalid product name"); } }
            case "price" -> { if(!(value instanceof BigDecimal price) || price.signum() <= 0) { throw new InvalidProduct("Price cannot be negative or null"); } }
            case "stock" -> { if(!(value instanceof Integer stock) || stock < 0) { throw new InvalidProduct("Stock cannot be negative"); } }
        }
    }

    private void validateUniqueName(String name, Long currentId) {
/* 
               for (Product53 product : repository.findAll()) {
            if (product.getName().equalsIgnoreCase(name)
                && !product.getId().equals(currentId)) {
            throw new DuplicateProduct("Product name already exists!"); }
        } 
*/
        repository.findByNameIgnoreCase(name.trim())
            .ifPresent(product -> { 
            if (!product.getId().equals(currentId)) {
                throw new DuplicateProduct("Product name already exists!"); 
            }
        });
    }

    private void validateRequest(ProductFields request) {
        if (request == null) { throw new InvalidProduct("Request cannot be empty"); }
        validateFields(request.name(),"name");
        validateFields(request.price(),"price");
        validateFields(request.stock(),"stock");
    } 

}
