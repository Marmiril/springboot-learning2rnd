package com.angel.springbootlearning.persistence3.ex54.dto;

import java.math.BigDecimal;

public record MovieUpdate(
    Integer stock,
    BigDecimal value
) {}
