package com.example.microservice.persistence.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka consumer configuration class
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * Creates a {@link ConsumerFactory} bean based on provided properties
     *
     * @param bootstrapAddress configured value
     * @return {@link ConsumerFactory} bean
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory(@Value(value = "${spring.kafka.bootstrap-servers:localhost}") final String bootstrapAddress) {
        Map<String, Object> mapConfig = new HashMap<>();
        mapConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        mapConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        mapConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(mapConfig);
    }

    /**
     * Creates a {@link ConcurrentKafkaListenerContainerFactory} bean
     *
     * @param consumerFactory {@link ConsumerFactory} bean
     * @return {@link ConcurrentKafkaListenerContainerFactory} bean
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(@Autowired final ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(consumerFactory);

        return listenerContainerFactory;
    }
}
