package com.wustzdy.springboot.validateCode.demo.bean.event;

import org.springframework.context.ApplicationEvent;

public class PayEvent extends ApplicationEvent {

    public PayEvent(Object source) {
        super(source);
    }
}