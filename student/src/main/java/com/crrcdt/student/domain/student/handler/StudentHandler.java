package com.crrcdt.student.domain.student.handler;

import com.crrcdt.common.event.SchoolStartedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/4 4:50 下午
 **/
@Service
@ProcessingGroup("my-processor")
public class StudentHandler {
    @EventHandler
    public void on(SchoolStartedEvent event) {
        System.out.println(event.getMessage());
    }
}
