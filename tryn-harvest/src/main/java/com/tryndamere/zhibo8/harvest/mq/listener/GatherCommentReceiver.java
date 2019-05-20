package com.tryndamere.zhibo8.harvest.mq.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryndamere.zhibo8.harvest.common.DateUtils;
import com.tryndamere.zhibo8.harvest.dto.NewsTotalDto;
import com.tryndamere.zhibo8.harvest.service.ICommentService;
import com.tryndamere.zhibo8.harvest.service.INewsService;
import com.tryndamere.zhibo8.harvest.vo.CommentPageVo;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.parser.strategy.NonPageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Author Dave
 * @Date 2019/1/27
 * @Description 消费到数据库
 */
public class GatherCommentReceiver {
    private final String PATTERN_TIME = "yyyy_MM_dd";

    @Autowired
    private ICommentService commentService;

    @Autowired
    INewsService newsService;
    private final Logger log = LoggerFactory.getLogger(GatherCommentReceiver.class);

    @RabbitListener(queues = "${rabbitmq.queue.gatherNews.name:tryn-gather-news-comment}", containerFactory = "rabbitListenerContainerFactory")
    public void receive(@Payload NewsTotalDto param) {
        log.info("gather news comment param :{}", param);
        IntStream.rangeClosed(0, param.getPageTotal())
                .forEach(e -> {
                    String url = "https://cache.zhibo8.cc/json/" + DateUtils.format(param.getCreateDate(), PATTERN_TIME) + "/news/nba/" + param.getFileName() + "_" + e + ".htm?key=" + Math.random();
                    new XxlCrawler.Builder()

                            .setUrls(url)
                            .setPageParser(new NonPageParser() {
                                @Override
                                public void parse(String url, String pageSource) {
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    try {
                                        List<GatherCommentVo> list = objectMapper.readValue(pageSource, new TypeReference<List<GatherCommentVo>>() {});
                                        commentService.saveComment(list,param.getNewsId());

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).build().start(false);

                });


    }


}
