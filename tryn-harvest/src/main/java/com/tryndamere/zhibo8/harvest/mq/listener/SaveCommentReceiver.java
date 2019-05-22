package com.tryndamere.zhibo8.harvest.mq.listener;

import com.tryndamere.zhibo8.harvest.entity.Comment;
import com.tryndamere.zhibo8.harvest.service.ICommentService;
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
public class SaveCommentReceiver {
    @Autowired
    ICommentService commentService;
    private final Logger log = LoggerFactory.getLogger(SaveCommentReceiver.class);

    @RabbitListener(queues = "${rabbitmq.queue.gatherNews.name:tryn-save-comment}",containerFactory = "rabbitListenerContainerFactory")
    public void receive(@Payload Comment param) {

        try {
            commentService.consumerComment(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
