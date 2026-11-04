package com.angel.springbootlearning.persistence2.ex53.exception;

import java.math.BigDecimal;

public class ProductNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProductNotFound(Long id) { super("There is no product with id: " + id); }
    public ProductNotFound(String name) { super("There is no product with name: " + name); }
    public ProductNotFound(BigDecimal price) { super("There is no product with price: " + price); }
}
