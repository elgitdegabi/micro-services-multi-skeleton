package com.example.microservice.persistence.dto;

import com.example.microservice.persistence.enums.ExecutionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Order message DTO
 */
@Getter
@Setter
@ToString
public class OrderMessage {
    private ExecutionType executionType;
    private Long id;
    private Long userId;
    private Long productId;
    private Long quantity;
}
