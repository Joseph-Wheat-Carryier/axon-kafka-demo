package com.crrcdt.school.domain.school;

import com.crrcdt.common.event.SchoolStartedEvent;
import com.crrcdt.school.domain.school.command.StartSchoolCommand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.Uuid;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/4 3:48 下午
 **/
@Aggregate
@Data
@NoArgsConstructor
public class SchoolAggregate {
    @AggregateIdentifier
    private String id;

    private String name;

    @CommandHandler
    public SchoolAggregate(StartSchoolCommand command) {
        this.id = Uuid.randomUuid().toString();
        AggregateLifecycle.apply(new SchoolStartedEvent());
    }
}
