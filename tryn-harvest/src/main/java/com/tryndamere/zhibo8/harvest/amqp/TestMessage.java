package com.tryndamere.zhibo8.harvest.amqp;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dave on 2019/1/7
 * Describes
 */
@RabbitListener(queues = "foo",containerFactory = "pointTaskContainerFactory")
@Configuration
public class TestMessage {

    @Bean
    public Sender mySender(){
        return new Sender();
    }

    @Bean
    public org.springframework.amqp.core.Queue fooQueue(){
        Map<String,Object> params=new HashMap<>();
        params.put("x-max-length",100);
        return new org.springframework.amqp.core.Queue("foo",false,false,true,params);
    }

    @RabbitHandler
    public void process(@Payload MessageDto foo) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + ": " + foo);
    }

    @Bean("pointTaskContainerFactory")
    public SimpleRabbitListenerContainerFactory pointTaskContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setPrefetchCount(20);
        factory.setConcurrentConsumers(10);
        configurer.configure(factory, connectionFactory);
        return factory;
    }




}
