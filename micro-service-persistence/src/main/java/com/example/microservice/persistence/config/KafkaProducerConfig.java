package com.example.microservice.persistence.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka producer configuration class
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * Creates a {@link ProducerFactory} bean based on provided properties
     *
     * @param bootstrapAddress configured value
     * @return {@link ProducerFactory} bean
     */
    @Bean
    public ProducerFactory<String, String> producerFactory(@Value(value = "${spring.kafka.bootstrap-servers:localhost}") final String bootstrapAddress) {
        Map<String, Object> mapConfig = new HashMap<>();
        mapConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        mapConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        mapConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(mapConfig);
    }

    /**
     * Creates a {@link KafkaTemplate} bean
     *
     * @param producerFactory {@link ProducerFactory} bean
     * @return {@link KafkaTemplate} bean
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(@Autowired final ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
