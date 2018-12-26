package com.tryndamere.zhibo8.harvest.processor;

import com.tryndamere.zhibo8.harvest.pipeline.CommentPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.List;
import java.util.Map;

/**
 * Created by Dave on 2018/12/24
 * Describes
 *
 * @author Dave
 */
public class FirstProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        Json json = page.getJson();
        List<Map> maps = json.toList(Map.class);
        page.putField("data", maps);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new FirstProcessor())
                .addPipeline(new CommentPipeline())
                .addUrl("https://cache.zhibo8.cc/json/2018_12_25/news/nba/5c21e43434d5e_0.htm?key=0.381771843516296")
                .thread(5)
                .run();

    }
}
