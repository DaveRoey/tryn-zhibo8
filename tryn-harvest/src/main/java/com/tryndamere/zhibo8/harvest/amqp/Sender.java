package com.tryndamere.zhibo8.harvest.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by Dave on 2019/1/7
 * Describes
 */
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Scheduled(fixedDelay = 1L)
    public void  send(){
        MessageDto msg = new MessageDto("AABBCCDLAFLA", "1", new Date());
        amqpTemplate.convertAndSend("foo",msg);
    }

}
