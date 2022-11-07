package com.crrcdt.school.controller;

import com.crrcdt.common.event.SchoolStartedEvent;

import com.crrcdt.school.domain.school.SchoolAggregate;
import com.crrcdt.school.domain.school.command.StartSchoolCommand;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.GenericDomainEventMessage;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.extensions.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.extensions.kafka.eventhandling.producer.ProducerFactory;
import org.axonframework.serialization.Serializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/3 9:21 上午
 **/
@RestController
@RequestMapping("/school")
@AllArgsConstructor
public class SchoolController {
    private final ProducerFactory<String, byte[]> producerFactory;

    private final KafkaMessageConverter<String, byte[]> msgConvert;

    private final EventGateway eventGateway;

    private final CommandGateway commandGateway;

    @PostMapping("/startSchool")
    public String startSchool() {
        commandGateway.send(new StartSchoolCommand());
        return "success";
    }
}
