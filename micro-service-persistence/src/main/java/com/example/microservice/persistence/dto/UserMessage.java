package com.example.microservice.persistence.dto;

import com.example.microservice.persistence.enums.ExecutionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User message DTO
 */
@Getter
@Setter
@ToString
public class UserMessage {
    private ExecutionType executionType;
    private Long id;
    private String name;
    private String alias;
    private String address;
}
