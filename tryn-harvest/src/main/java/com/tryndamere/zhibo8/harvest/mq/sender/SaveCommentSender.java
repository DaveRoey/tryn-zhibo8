package com.tryndamere.zhibo8.harvest.mq.sender;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Dave
 * @Date 2019/1/27
 * @Description
 */
@Component
public class SaveCommentSender {
    @Value("${rabbitmq.queue.deviceStatusDataBase.bindingKey:tryn.save.comment}")
    private String gatherNewsRouteKey;
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange saveCommentExchange;


    public void send(Object message) {
        template.convertAndSend(saveCommentExchange.getName(), gatherNewsRouteKey, message);
    }
}
