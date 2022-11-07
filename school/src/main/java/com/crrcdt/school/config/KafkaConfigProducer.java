package com.crrcdt.school.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.producer.DefaultProducerFactory;
import org.axonframework.extensions.kafka.eventhandling.producer.KafkaEventPublisher;
import org.axonframework.extensions.kafka.eventhandling.producer.KafkaPublisher;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Optional;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/4 3:17 下午
 **/
@Configuration
public class KafkaConfigProducer {
    @Bean
    public ProducerFactory<String, byte[]> producerFactory(KafkaProperties kafkaProperties) {
        kafkaProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        return DefaultProducerFactory.<String, byte[]>builder()
                .closeTimeout(Duration.ofSeconds(30))                 // Defaults to "30" seconds
                .producerCacheSize(20)       // Defaults to "10"; only used for "TRANSACTIONAL" mode
                .configuration(kafkaProperties.buildProducerProperties())       // Hard requirement
                .confirmationMode(kafkaProperties.getPublisher().getConfirmationMode())         // Defaults to a Confirmation Mode of "NONE"
                .transactionalIdPrefix(kafkaProperties.getProducer().getTransactionIdPrefix()) // Hard requirement when in "TRANSACTIONAL" mode
                .build();
    }

    @Bean
    public KafkaPublisher<String, byte[]> kafkaPublisher(@Qualifier("jacksonSerializer") Serializer jacksonSerializer,
                                                         KafkaProperties kafkaProperties,
                                                         ProducerFactory<String, byte[]> producerFactory,
                                                         KafkaMessageConverter<String, byte[]> kafkaMessageConverter) {
        return KafkaPublisher.<String, byte[]>builder()
                .serializer(jacksonSerializer)
                .topicResolver(m -> Optional.of(kafkaProperties.getDefaultTopic()))
                .producerFactory(producerFactory)           // Hard requirement
                .messageConverter(kafkaMessageConverter)    // Defaults to a "DefaultKafkaMessageConverter"
                .publisherAckTimeout(1000)   // Defaults to "1000" milliseconds; only used for "WAIT_FOR_ACK" mode
                .build();
    }

    @Bean
    public KafkaEventPublisher<String, byte[]> kafkaEventPublisher(KafkaPublisher<String, byte[]> kafkaPublisher,
                                                                   EventProcessingConfigurer eventProcessingConfigurer) {
        KafkaEventPublisher<String, byte[]> publisher = KafkaEventPublisher.<String, byte[]>builder()
                .kafkaPublisher(kafkaPublisher)
                .build();
        this.registerPublisherToEventProcessor(eventProcessingConfigurer, publisher);
        return publisher;
    }

    private void registerPublisherToEventProcessor(EventProcessingConfigurer eventProcessingConfigurer,
                                                   KafkaEventPublisher<String, byte[]> kafkaEventPublisher) {
        String processingGroup = KafkaEventPublisher.DEFAULT_PROCESSING_GROUP;
        eventProcessingConfigurer.registerEventHandler(configuration -> kafkaEventPublisher)
                .assignHandlerTypesMatching(
                        processingGroup,
                        clazz -> clazz.isAssignableFrom(KafkaEventPublisher.class)
                )
                .registerSubscribingEventProcessor(processingGroup);
//         Replace `registerSubscribingEventProcessor` for `registerTrackingEventProcessor` to use a tracking processor
    }
}
