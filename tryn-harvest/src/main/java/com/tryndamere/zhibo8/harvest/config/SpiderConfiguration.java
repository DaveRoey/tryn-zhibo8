package com.tryndamere.zhibo8.harvest.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryndamere.zhibo8.harvest.mq.sender.GatherNewsSender;
import com.tryndamere.zhibo8.harvest.vo.CommentPageVo;
import com.tryndamere.zhibo8.harvest.vo.GatherCommentVo;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.parser.PageParser;
import com.xuxueli.crawler.parser.strategy.NonPageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;

/**
 * @Author Dave
 * @Date 2019/5/10
 * @Description
 */
@Configuration
public class SpiderConfiguration {
    @Autowired
    private GatherNewsSender gatherNewsSender;

    @Bean(name = "gatherNewsCrawler")
    public XxlCrawler gatherNewsCrawler() {
        return new XxlCrawler.Builder()
                .setUrls("https://news.zhibo8.cc/nba/more.htm")
                .setAllowSpread(true)
                .setWhiteUrlRegexs("https://news.zhibo8.cc/nba/.*/.*.htm")
                .setThreadCount(1)
                .setPauseMillis(1000)
                .setTimeoutMillis(5000)
                .setPageParser(new PageParser<GatherNewsVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, GatherNewsVo vo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = html.baseUri();
                        vo.setUrl(pageUrl);
                        gatherNewsSender.send(vo);
                    }
                }).build();
    }



    @Bean(name = "gatherCommentCrawler")
    public XxlCrawler gatherCommentCrawler() {
        return new XxlCrawler.Builder()
                .setUrls("https://cache.zhibo8.cc/json/2019_05_11/news/nba/5cd62995cc0fe_0.htm?key=" + Math.random())
                .setPageParser(new NonPageParser() {
                    @Override
                    public void parse(String url, String pageSource) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            List<GatherCommentVo> list = objectMapper.readValue(pageSource, new TypeReference<List<GatherCommentVo>>() {
                            });
                            int sum = 0;
                            for (GatherCommentVo gatherCommentVo : list) {
                                System.out.println(gatherCommentVo);
                                sum++;
                                if (!gatherCommentVo.getChildren().isEmpty()) {
                                    sum += gatherCommentVo.getChildren().size();
                                }
                            }
                            System.out.println(sum);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).build();
    }

    @Bean(name = "getCommentCountCrawler")
    public XxlCrawler getCommentCountCrawler() {
        return new XxlCrawler.Builder()
                .setUrls("https://cache.zhibo8.cc/json/2019_05_11/news/nba/5cd62995cc0fe_count.htm?key=0.8399331951805584")
                .setPageParser(new NonPageParser() {
                    @Override
                    public void parse(String url, String pageSource) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            CommentPageVo pageVo = objectMapper.readValue(pageSource, CommentPageVo.class);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).build();
    }

    public static void main(String[] args) {
        int pageno = (int) (Math.ceil(101 / 100) - 1);
        System.out.println("pageno = " + pageno);

    }
}
