package com.tryndamere.zhibo8.harvest.mq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryndamere.zhibo8.harvest.common.DateUtils;
import com.tryndamere.zhibo8.harvest.dto.NewsTotalDto;
import com.tryndamere.zhibo8.harvest.mq.sender.GatherCommentSender;
import com.tryndamere.zhibo8.harvest.service.INewsCountInfoService;
import com.tryndamere.zhibo8.harvest.service.INewsService;
import com.tryndamere.zhibo8.harvest.vo.CommentPageVo;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.parser.strategy.NonPageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Random;

/**
 * @Author Dave
 * @Date 2019/1/27
 * @Description 消费到数据库
 */
public class GatherNewsTotalReceiver {
    private final String PATTERN_TIME = "yyyy_MM_dd";
    @Autowired
    INewsService newsService;
    @Autowired
    private GatherCommentSender gatherCommentSender;
    @Autowired
    private INewsCountInfoService newsCountInfoService;
    private final Logger log = LoggerFactory.getLogger(GatherNewsTotalReceiver.class);

    @RabbitListener(queues = "${rabbitmq.queue.gatherNews.name:tryn-gather-news-total}")
    public void receive(@Payload NewsTotalDto param) {
        log.info("gather news total :{}", param);
        String url = "https://cache.zhibo8.cc/json/" + DateUtils.format(param.getCreateDate(), PATTERN_TIME) + "/news/nba/" + param.getFileName() + "_count.htm?key=" + Math.random();
        new XxlCrawler.Builder()
                .setUrls(url)
                .setPageParser(new NonPageParser() {
                    @Override
                    public void parse(String url, String pageSource) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            //解析data
                            CommentPageVo pageVo = objectMapper.readValue(pageSource, CommentPageVo.class);

                            //计算新闻页数
                            int pageTotal = (int) (Math.ceil(pageVo.getRootNum() / 100.0) - 1);
                            param.setPageTotal(pageTotal);

                            //保存新闻记录信息
                            newsCountInfoService.saveNewsCountInfo(pageVo,param.getNewsId());

                            gatherCommentSender.send(param);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).build().start(true);

    }


}
