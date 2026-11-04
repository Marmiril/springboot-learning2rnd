package com.angel.springbootlearning.persistence2.ex53.dto;

import java.math.BigDecimal;

public record ProductResponse53(
    Long id,
    String name,
    BigDecimal price,
    Integer stock    
) {}
