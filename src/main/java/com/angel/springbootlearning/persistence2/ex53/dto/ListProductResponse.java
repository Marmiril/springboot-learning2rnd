package com.angel.springbootlearning.persistence2.ex53.dto;

import java.util.List;

public record ListProductResponse(
    List<ProductResponse> products
) {}
