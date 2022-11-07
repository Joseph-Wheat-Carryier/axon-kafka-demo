package com.crrcdt.school.config;

import org.axonframework.config.Configurer;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.extensions.kafka.KafkaProperties;
import org.axonframework.extensions.kafka.configuration.KafkaMessageSourceConfigurer;
import org.axonframework.extensions.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.consumer.AsyncFetcher;
import org.axonframework.extensions.kafka.eventhandling.consumer.ConsumerFactory;
import org.axonframework.extensions.kafka.eventhandling.consumer.DefaultConsumerFactory;
import org.axonframework.extensions.kafka.eventhandling.consumer.Fetcher;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.KafkaEventMessage;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.SortedKafkaMessageBuffer;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.axonframework.extensions.kafka.eventhandling.consumer.subscribable.SubscribableKafkaMessageSource;
import org.axonframework.extensions.kafka.eventhandling.producer.KafkaEventPublisher;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/4 2:05 下午
 **/
@Configuration
public class KafkaConfigConsumer {
//    @Bean
//    // ...
//    public ConsumerFactory<String, byte[]> consumerFactory(KafkaProperties kafkaProperties) {
//        return new DefaultConsumerFactory<>(kafkaProperties.buildConsumerProperties());
//    }
//    // ...
//
//    @Bean
//    // ...
//    public Fetcher<?, ?, ?> fetcher() {
//        ThreadFactory threadFactory = new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "thread-pool");
//            }
//        };
//        ExecutorService pool = new ThreadPoolExecutor(5, 200,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
//
//        return AsyncFetcher.builder()
//                .pollTimeout(5000)          // Defaults to "5000" milliseconds
//                .executorService(pool)    // Defaults to a cached thread pool executor
//                .build();
//    }
//
//    @Bean
//    public KafkaMessageSourceConfigurer kafkaMessageSourceConfigurer() {
//        return new KafkaMessageSourceConfigurer();
//    }
//
//    @Bean
//    public StreamableKafkaMessageSource<String, byte[]> subscribableKafkaMessageSource(KafkaProperties kafkaProperties,
//                                                                                       List<String> topics,
//                                                                                       ConsumerFactory<String, byte[]> consumerFactory,
//                                                                                       Fetcher<String, byte[], KafkaEventMessage> fetcher,
//                                                                                       KafkaMessageConverter<String, byte[]> messageConverter,
//                                                                                       KafkaMessageSourceConfigurer kafkaMessageSourceConfigurer,
//                                                                                       EventProcessingConfigurer processingConfigurer,
//                                                                                       Serializer jacksonSerializer) {
//        List<String> topicList = new ArrayList<>();
//        topicList.add("helloworld");
//        StreamableKafkaMessageSource<String, byte[]> subscribableKafkaMessageSource = StreamableKafkaMessageSource.<String, byte[]>builder()
//                .serializer(jacksonSerializer)
//                .topics(topicList)                     // Defaults to a collection of "Axon.Events"
////                .groupId("consumer")                   // Hard requirement
//                .consumerFactory(consumerFactory)   // Hard requirement
//                .fetcher(fetcher)                   // Hard requirement
//                .messageConverter(messageConverter) // Defaults to a "DefaultKafkaMessageConverter"
//                .bufferFactory(() -> new SortedKafkaMessageBuffer<>(1000))
//                .build();
//        // Registering the source is required to tie into the Configurers lifecycle to start the source at the right stage
//        return subscribableKafkaMessageSource;
//    }
}
