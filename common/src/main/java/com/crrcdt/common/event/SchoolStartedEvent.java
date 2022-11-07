package com.crrcdt.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Description:
 * @Author: juliuming
 * @Date: 2022/11/3 9:20 上午
 **/
@AllArgsConstructor
@Data
public class SchoolStartedEvent {
    @Getter
    String message;

    public SchoolStartedEvent() {
        this.message = "开学了";
    }
}
