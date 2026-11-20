package com.angel.springbootlearning.persistence3.ex54.dto;

import java.math.BigDecimal;

public record MovieDataResponse(
    Long id,
    String name, 
    String director,
    Integer year,
    Integer stock,
    BigDecimal value
) {}
