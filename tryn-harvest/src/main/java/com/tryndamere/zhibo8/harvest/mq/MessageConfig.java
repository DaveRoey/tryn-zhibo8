package com.tryndamere.zhibo8.harvest.mq;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.*;
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

    @Value("${rabbitmq.queue.gatherNews.name:tryn-gather-news-total}")
    private String gatherNewsTotalQueue;

    @Value("${rabbitmq.queue.gatherNews.name:tryn-gather-news-comment}")
    private String gatherCommentQueue;


    @Value("${rabbitmq.queue.deviceStatusDataBase.bindingKey:tryn.gather.news}")
    private String gatherNewsBindingKey;


    @Value("${rabbitmq.queue.deviceStatusDataBase.bindingKey:tryn.gather.news.total}")
    private String gatherNewsTotalBindingKey;

    @Value("${rabbitmq.queue.deviceStatusDataBase.bindingKey:tryn.gather.news.comment}")
    private String gatherCommentBindingKey;

    /**
     * 数据采集交换机
     *
     * @return
     */
    @Bean
    public TopicExchange gatherChange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * 数据采集交换机
     *
     * @return
     */
    @Bean
    public DirectExchange saveCommentExchange() {
        return new DirectExchange("tryn.comment");
    }


    @Bean
    public Queue gatherNewsQueue() {
        return new Queue(gatherNewsQueue, false, false, false, Maps.newHashMap());
    }

    @Bean
    public Queue gatherNewsTotalQueue() {
        return new Queue(gatherNewsTotalQueue, false, false, false, Maps.newHashMap());
    }

    @Bean
    public Queue gatherCommentQueue() {
        return new Queue(gatherCommentQueue, false, false, false, Maps.newHashMap());
    }

    @Bean
    public Queue saveCommentQueue() {
        return new Queue("tryn-save-comment", false, false, false, Maps.newHashMap());
    }

    @Bean
    public Binding deviceStatusDatabaseBinding(TopicExchange gatherChange, Queue gatherNewsQueue) {
        return BindingBuilder.bind(gatherNewsQueue)
                .to(gatherChange)
                .with(gatherNewsBindingKey);
    }

    @Bean
    public Binding gatherNewsTotalBinding(TopicExchange gatherChange, Queue gatherNewsTotalQueue) {
        return BindingBuilder.bind(gatherNewsTotalQueue)
                .to(gatherChange)
                .with(gatherNewsTotalBindingKey);
    }

    @Bean
    public Binding gatherCommentBinding(TopicExchange gatherChange, Queue gatherCommentQueue) {
        return BindingBuilder.bind(gatherCommentQueue)
                .to(gatherChange)
                .with(gatherCommentBindingKey);
    }

    @Bean
    public Binding saveCommentBinding(DirectExchange saveCommentExchange, Queue saveCommentQueue) {
        return BindingBuilder.bind(saveCommentQueue)
                .to(saveCommentExchange)
                .with("tryn.save.comment");
    }

}
