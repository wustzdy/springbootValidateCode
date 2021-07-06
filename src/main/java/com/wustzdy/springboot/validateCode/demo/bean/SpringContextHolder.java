package com.wustzdy.springboot.validateCode.demo.bean;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     *      * 取得存储在静态变量中的ApplicationContext.
     *      
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     *      * 清除SpringContextHolder中的ApplicationContext为Null.
     * <p>
     *      
     */

    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 发布事件
     *
     * @param applicationEvent
     */
    public static void publishEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent != null) {
            applicationContext.publishEvent(applicationEvent);
        }
        return;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @SneakyThrows
    @Override
    public void destroy() {
        SpringContextHolder.clearHolder();
    }
}

