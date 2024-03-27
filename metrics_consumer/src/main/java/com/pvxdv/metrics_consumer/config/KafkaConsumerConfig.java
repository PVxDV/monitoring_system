package com.pvxdv.metrics_consumer.config;


import com.pvxdv.metrics_consumer.event.ListOfMetricEvent;
import com.pvxdv.metrics_consumer.exception.NonRetryableException;
import com.pvxdv.metrics_consumer.exception.RetryableException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    private final Environment environment;

    @Bean
    public ConsumerFactory<String, ListOfMetricEvent> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("spring.kafka.consumer.bootstrap-servers"));
        config.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("spring.kafka.consumer.group-id"));

        DefaultKafkaConsumerFactory<String, ListOfMetricEvent> cf = new DefaultKafkaConsumerFactory<>(config,
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(String.class)
                        .forKeys()
                        .ignoreTypeHeaders()),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ListOfMetricEvent.class)
                        .trustedPackages(environment.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages"))
                        .ignoreTypeHeaders())
        );
        return cf;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ListOfMetricEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, ListOfMetricEvent> consumerFactory, KafkaTemplate<String, Object> kafkaTemplate) {
        ConcurrentKafkaListenerContainerFactory<String, ListOfMetricEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate),
                new FixedBackOff(3000, 5));
        errorHandler.addNotRetryableExceptions(NonRetryableException.class);
        errorHandler.addRetryableExceptions(RetryableException.class);

        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("spring.kafka.consumer.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }
}
