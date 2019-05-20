package com.tryndamere.zhibo8.harvest.mq.listener;

import com.tryndamere.zhibo8.harvest.service.INewsService;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @Author Dave
 * @Date 2019/1/27
 * @Description 消费到数据库
 */
public class GatherNewsReceiver {
    @Autowired
    INewsService newsService;
    private final Logger log = LoggerFactory.getLogger(GatherNewsReceiver.class);

    @RabbitListener(queues = "${rabbitmq.queue.gatherNews.name:tryn-gather-news}", containerFactory = "rabbitListenerContainerFactory")
    public void receive(@Payload GatherNewsVo param) {
        log.info("device runtime status consumer on database :{}", param);
        if (param.getTitle() == null) {
            //战报
        } else {
            newsService.saveNews(param);
        }

    }


}
