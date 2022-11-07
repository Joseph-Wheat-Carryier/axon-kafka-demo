package com.crrcdt.student.config;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.inmemory.InMemoryTokenStore;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(KafkaConfigConsumer.class)
public class AxonConfig {

    @Autowired
    public void configureProcessorDefault(EventProcessingConfigurer processingConfigurer,
                                          StreamableKafkaMessageSource<String, byte[]> messageSource) {
        processingConfigurer.usingPooledStreamingEventProcessors();
        processingConfigurer
                // ... to define a specific StreamableMessageSource ...
                .registerPooledStreamingEventProcessor(
                        "my-processor", conf -> messageSource/* create/return StreamableMessageSource */
                );
    }

    @Bean
    public TokenStore myTokenStore() {
        return new InMemoryTokenStore();
    }
}