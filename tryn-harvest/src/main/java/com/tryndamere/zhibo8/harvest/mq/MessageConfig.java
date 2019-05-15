package com.tryndamere.zhibo8.harvest.mq;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author Dave
 * @Date 2019/1/27
 * @Description
 */
@Configuration
public class MessageConfig {
    @Value("${rabbitmq.exchange:tryn.gather}")
    private String exchangeName;

    @Value("${rabbitmq.queue.gatherNews.name:tryn-gather-news}")
    private String gatherNewsQueue;


    @Value("${rabbitmq.queue.deviceStatusDataBase.bindingKey:tryn.gather.news}")
    private String gatherNewsBindingKey;


    /**
     * 数据采集交换机
     *
     * @return
     */
    @Bean
    public TopicExchange gatherChange() {
        return new TopicExchange(exchangeName);
    }


    @Bean
    public Queue gatherNewsQueue() {
        return new Queue(gatherNewsQueue, false, false, false, Maps.newHashMap());
    }


    @Bean
    public Binding deviceStatusDatabaseBinding(TopicExchange gatherChange, Queue gatherNewsQueue) {
        return BindingBuilder.bind(gatherNewsQueue)
                .to(gatherChange)
                .with(gatherNewsBindingKey);
    }


}
