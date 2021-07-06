package com.wustzdy.springboot.validateCode.demo.bean.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * 库存监听者
 */
//事件监听器(方式二：@EventListener)：

/**
 * 异步监听日志事件
 */
@Slf4j
//@Component
public class StockListener {
    /*@Async
    @EventListener(PayEvent.class)
    public void reduceStock(PayEvent payEvent) {
        log.info("库存服务监听到支付成功事件：{}", payEvent.getSource());
        try {
            log.info("开始减少库存");
            Thread.sleep(2000);
            log.info("库存减少完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}

//StockListener 和SysLogininforEventListener 任意选一个 都是事件监听
