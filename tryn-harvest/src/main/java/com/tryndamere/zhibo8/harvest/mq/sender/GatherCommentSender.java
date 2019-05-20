package com.tryndamere.zhibo8.harvest.mq.sender;

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
public class GatherCommentSender {
    @Value("${rabbitmq.queue.deviceStatusDataBase.bindingKey:tryn.gather.news.comment}")
    private String gatherNewsRouteKey;
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange gatherChange;


    public void send(Object message) {
        template.convertAndSend(gatherChange.getName(), gatherNewsRouteKey, message);
    }
}
