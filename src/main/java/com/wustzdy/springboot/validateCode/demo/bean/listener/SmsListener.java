package com.wustzdy.springboot.validateCode.demo.bean.listener;

import com.wustzdy.springboot.validateCode.demo.bean.event.PayEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 短信监听者
 */
@Slf4j
@Component
public class SmsListener {

    @Async
    @EventListener(PayEvent.class)
    public void sendSms(PayEvent payEvent) {
        log.info("短信服务监听到支付成功事件：{}", payEvent.getSource());
        try {
            log.info("开始发送短信");
            Thread.sleep(3000);
            log.info("短信发送成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

