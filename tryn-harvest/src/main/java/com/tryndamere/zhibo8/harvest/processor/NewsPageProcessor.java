package com.tryndamere.zhibo8.harvest.processor;

import com.sun.org.apache.regexp.internal.RE;
import com.tryndamere.zhibo8.harvest.pipeline.NewsPagePipeline;
import com.tryndamere.zhibo8.tryncommon.utils.DateHelper;
import com.tryndamere.zhibo8.trynmodel.entity.NewsPage;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dave on 2018/12/26
 * Describes 滚动新闻页面解析
 *
 * @author Dave
 */
@Component
public class NewsPageProcessor implements PageProcessor {
    private String newsItemPattern;
    private String titlePattern;
    private String filterPattern;
    private String urlPattern;
    private String releaseTimePattern;

    NewsPageProcessor() {
        init();
    }

    private void init() {
        this.newsItemPattern = "#boxlist > div.dataList > ul:nth-child(1) > li";
        this.titlePattern = "//a[1]/text()";
        this.filterPattern = "https://news.zhibo8.cc/nba/.*/\\d*.htm";
        this.urlPattern = "li a";
        this.releaseTimePattern="//span[3]/text()";
    }


    private Site site = new Site().setDomain("https://www.zhibo8.cc/").setRetryTimes(3).setTimeOut(500);

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().$(newsItemPattern).nodes();

        List<NewsPage> morePages = new ArrayList<>();
        nodes.forEach(e -> {
            String url = e.$(urlPattern).links().toString();
            String title = e.xpath(titlePattern).toString();
            String releaseTime = e.xpath(releaseTimePattern).toString();

            NewsPage morePage = new NewsPage();
            //筛选出比赛的url
            if (!url.matches(filterPattern)) {
                morePage.setUrl(url);
                morePage.setTitle(title);
                morePages.add(morePage);
                morePage.setReleaseTime(DateHelper.dateFormat(releaseTime));
            }

        });
        page.putField("more", morePages);


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewsPageProcessor())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://news.zhibo8.cc/nba/more.htm")
                .run();
    }

}
