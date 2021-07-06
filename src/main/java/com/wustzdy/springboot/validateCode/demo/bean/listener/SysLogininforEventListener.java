package com.wustzdy.springboot.validateCode.demo.bean.listener;

import com.wustzdy.springboot.validateCode.demo.bean.event.PayEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//事件监听器(方式一：实现ApplicationListener)：
@Component
@Slf4j
public class SysLogininforEventListener implements ApplicationListener<PayEvent> {
    @Override
    @Async
    public void onApplicationEvent(PayEvent payEvent) {
        log.info("库存服务监听到支付成功事件：{}", payEvent.getSource());
        try {
            log.info("开始减少库存");
            Thread.sleep(2000);
            log.info("库存减少完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
