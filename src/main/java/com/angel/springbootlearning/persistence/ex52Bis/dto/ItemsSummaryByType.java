package com.angel.springbootlearning.persistence.ex52Bis.dto;

import com.angel.springbootlearning.persistence.ex52Bis.models.ItemType;

public record ItemsSummaryByType(
    ItemType type,
    Long totalInventory,
    Double totalValue
) {

}
