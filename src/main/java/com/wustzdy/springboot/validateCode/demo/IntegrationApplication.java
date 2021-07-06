package com.wustzdy.springboot.validateCode.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//开启异步
@SpringBootApplication
public class IntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }
}

//https://blog.csdn.net/weixin_43072970/article/details/106848753
/*
发布订阅模式可能大家都熟悉，消息队列、redis等很多中间件都有发布订阅模式，但你知道我们平时用的spring也有发布订阅模式吗？

        在我们系统中，可能会遇到处理完一个流程以后，接下来要同时处理多个流程，比如用户支付成功以后，接下来会同时减少库存、发送下单成功短信等，这种情况就可以用到发布订阅。减少库存和发送下单成功短信服务监听支付成功事件，当用户支付成功以后，发布这个消息，另外两个服务则会监听到，接着执行对应的逻辑。

        可能在真实情况下，我们应用都是分布式的，处理这种情况可能都是使用消息队列等中间件来处理，但是有时候在单体应用中，使用spring的发布订阅也未尝不是个好的选择。
        定义事件监听者
        当支付成功以后，会减少库存和发送短信，这里定义两个监听者*/
