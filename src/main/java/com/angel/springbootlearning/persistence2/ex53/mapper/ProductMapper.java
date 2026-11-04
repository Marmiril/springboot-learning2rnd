package com.angel.springbootlearning.persistence2.ex53.mapper;

import com.angel.springbootlearning.persistence2.ex53.dto.ProductDataResponse;
import com.angel.springbootlearning.persistence2.ex53.dto.ProductRequest;
import com.angel.springbootlearning.persistence2.ex53.model.Product53;

public class ProductMapper {

    public Product53 toModel(ProductRequest request) {
        return new Product53(            
            request.name().trim(),
            request.price(),
            request.stock()
        );
    }

    public Product53 toUpdate(ProductRequest request) {
        return new Product53(
            request.name(),
            request.price(),
            request.stock()
        );
    }

    public ProductDataResponse toResponse(Product53 product) {
        return new ProductDataResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock()
        );
    }
    
}
