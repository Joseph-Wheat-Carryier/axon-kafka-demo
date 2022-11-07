package com.crrcdt.school.config;

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.extensions.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.config.SpringAxonConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/3 9:04 上午
 **/
@Configuration
public class EventSourceConfig {
    @Bean
    @Primary
    public Serializer jacksonSerializer() {
        return JacksonSerializer.builder().build();
    }

    // The Event store `EmbeddedEventStore` delegates actual storage and retrieval of events to an `EventStorageEngine`.
    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, SpringAxonConfiguration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.getObject().messageMonitor(EventStore.class, "eventStore"))
                        .build();
    }

    // The `InMemoryEventStorageEngine` stores each event in memory
    @Bean
    public EventStorageEngine storageEngine() {
        return new InMemoryEventStorageEngine();
    }


    @Bean
    public KafkaMessageConverter<String, byte[]> kafkaMessageConverter(Serializer jacksonSerializer) {
        return DefaultKafkaMessageConverter.builder()
                .serializer(jacksonSerializer)                                      // Hard requirement
                .build();
    }
}
