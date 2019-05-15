package com.tryndamere.zhibo8.harvest;

import com.xuxueli.crawler.XxlCrawler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HarvestApplicationTests {

    @Resource(name = "gatherNewsCrawler")
    private XxlCrawler xxlCrawler;
    @Resource(name = "gatherCommentCrawler")
    private XxlCrawler gatherCommentCrawler;
    @Resource(name = "getCommentCountCrawler")
    private XxlCrawler getCommentCountCrawler;

    @Test
    public void contextLoads() {
        xxlCrawler.start(true);
    }

    @Test
    public void gatherComment() {
        gatherCommentCrawler.start(true);
    }

    @Test
    public void gatherCommentPage() {
        getCommentCountCrawler.start(true);
    }


}

