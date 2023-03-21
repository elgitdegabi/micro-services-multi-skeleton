package com.example.microservice.persistence.service;

import com.example.microservice.persistence.dto.ResultMessage;
import com.example.microservice.persistence.enums.HeaderType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Producer service
 */
@Slf4j
@Service
public class ProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String resultTopic;
    private ObjectMapper objectMapper = new ObjectMapper();

    public ProducerService(@Autowired final KafkaTemplate<String, String> kafkaTemplate,
                           @Value(value = "${spring.kafka.topic.result.name:none}") final String resultTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.resultTopic = resultTopic;
    }

    /**
     * Publishes a Kafka message with provided values
     * @param flow {@link String}
     * @param sessionId {@link String}
     * @param resultMessage {@link ResultMessage}
     */
    public void publish(final String flow, final String sessionId, final ResultMessage resultMessage) {
        try {
            log.info("publishing result: {}", resultMessage);

            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(resultTopic, objectMapper.writeValueAsString(resultMessage));
            producerRecord.headers().add(HeaderType.FLOW.name(), flow.getBytes());
            producerRecord.headers().add(HeaderType.SESSION_ID.name(), sessionId.getBytes());

            kafkaTemplate.send(producerRecord);
        } catch (Exception e) {
            log.error("Error publishing message: " + resultMessage, e);
        }
    }
}
