package com.example.microservice.persistence.dto;

import com.example.microservice.persistence.enums.ResultStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Result message DTO
 */
@Getter
@Setter
@ToString
public class ResultMessage {
    private ResultStatus resultStatus;
    private String sessionId;
    private String originalMessage;
    private String resultMessage;
}
