package com.example.microservice.persistence.service;

import com.example.microservice.persistence.dto.OrderMessage;
import com.example.microservice.persistence.dto.ProductMessage;
import com.example.microservice.persistence.dto.ResultMessage;
import com.example.microservice.persistence.dto.UserMessage;
import com.example.microservice.persistence.enums.FlowType;
import com.example.microservice.persistence.enums.HeaderType;
import com.example.microservice.persistence.enums.ResultStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * Consumer service
 */
@Slf4j
@Service
public class ConsumerService {

    private PersistenceService persistenceService;
    private ProducerService producerService;
    private ObjectMapper objectMapper = new ObjectMapper();


    public ConsumerService(
            @Autowired final PersistenceService persistenceService,
            @Autowired final ProducerService producerService
    ) {
        this.persistenceService = persistenceService;
        this.producerService = producerService;
    }

    /**
     * Consumes Kafka messages from execution topic
     *
     * @param consumerRecord {@link ConsumerRecord}
     */
    @KafkaListener(topics = "${spring.kafka.queue.execution.name}", groupId = "${spring.kafka.queue.execution.group-id}")
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        String message = consumerRecord.value();
        log.info("consume - received message: {}", message);

        String flow = getFlow(consumerRecord.headers().toArray());
        String sessionId = getSessionId(consumerRecord.headers().toArray());
        ResultMessage resultMessage;

        try {
            resultMessage = execute(flow, sessionId, message);
        } catch (Exception e) {
            log.error("consume - error parsing message: " + message);

            resultMessage = new ResultMessage();
            resultMessage.setSessionId(sessionId);
            resultMessage.setOriginalMessage(message);
            resultMessage.setResultStatus(ResultStatus.ERROR);
            resultMessage.setResultMessage("error parsing message");
        }

        producerService.publish(flow, sessionId, resultMessage);
        log.info("consume - published result message: {}", resultMessage);
    }

    /**
     * Executes flow based on provided flow value
     *
     * @param flow      {@link String}
     * @param sessionId {@link String}
     * @param message   {@link String}
     * @return {@link ResultMessage} with result of execution
     */
    private ResultMessage execute(final String flow, final String sessionId, final String message) throws Exception {
        switch (FlowType.valueOf(flow)) {
            case USER:
                return persistenceService.execute(sessionId, objectMapper.readValue(message, UserMessage.class));
            case PRODUCT:
                return persistenceService.execute(sessionId, objectMapper.readValue(message, ProductMessage.class));
            case ORDER:
                return persistenceService.execute(sessionId, objectMapper.readValue(message, OrderMessage.class));
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Gets flow value from provided headers
     *
     * @param headers {@link Header[]}
     * @return {@link String} flow value
     */
    private String getFlow(final Header[] headers) {
        Optional<Header> flow = Arrays.stream(headers).filter(h -> h.key().equalsIgnoreCase(HeaderType.FLOW.name())).findAny();
        return flow.isPresent() ? new String(flow.get().value()) : ResultStatus.UNDEFINED.name();
    }

    /**
     * Gets session id value from provided headers
     *
     * @param headers {@link Header[]}
     * @return {@link String} session id value
     */
    private String getSessionId(final Header[] headers) {
        Optional<Header> sessionId = Arrays.stream(headers).filter(h -> h.key().equalsIgnoreCase(HeaderType.SESSION_ID.name())).findAny();
        return sessionId.isPresent() ? new String(sessionId.get().value()) : ResultStatus.UNDEFINED.name();
    }
}
