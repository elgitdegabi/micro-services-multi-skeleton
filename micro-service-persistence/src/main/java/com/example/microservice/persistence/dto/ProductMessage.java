package com.example.microservice.persistence.dto;

import com.example.microservice.persistence.enums.ExecutionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Product message DTO
 */
@Getter
@Setter
@ToString
public class ProductMessage {
    private ExecutionType executionType;
    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private BigDecimal price;
}
