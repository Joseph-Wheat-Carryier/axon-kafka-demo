package com.crrcdt.school.domain.school.handler;

import com.crrcdt.common.event.SchoolStartedEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/4 4:00 下午
 **/
@Service
@AllArgsConstructor
@ProcessingGroup("my-handlers")
public class SchoolEventHandler {
    private final ProducerFactory<String, byte[]> producerFactory;

    private final KafkaMessageConverter<String, byte[]> converter;
}
