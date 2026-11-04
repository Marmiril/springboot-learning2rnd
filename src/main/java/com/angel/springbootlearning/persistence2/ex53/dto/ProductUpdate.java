package com.angel.springbootlearning.persistence2.ex53.dto;

import java.math.BigDecimal;

public record ProductUpdate(
    String name,
    BigDecimal price,
    Integer stock
) {}
