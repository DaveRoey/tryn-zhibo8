package com.tryndamere.zhibo8.harvest.processor;

import com.tryndamere.zhibo8.harvest.pipeline.NewsPagePipeline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import static org.junit.Assert.*;

/**
 * Created by Dave on 2018/12/26
 * Describes
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsPageProcessorTest {
    @Autowired
    private NewsPagePipeline newsPagePipeline;

    @Test
    public void testDig() throws Exception {
        Spider.create(new NewsPageProcessor())
                .addPipeline(newsPagePipeline)
                .addUrl("https://news.zhibo8.cc/nba/more.htm")
                .run();
    }

}