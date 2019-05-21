package com.tryndamere.zhibo8.harvest.handler;

import com.google.common.base.Strings;
import com.tryndamere.zhibo8.harvest.mq.sender.GatherNewsSender;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.parser.PageParser;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author Dave
 * @Date 2018/12/27
 * @Description
 */
@JobHandler(value = "gatherNewsHandler")
@Component
public class GatherNewsHandler extends IJobHandler {
    @Autowired
    private GatherNewsSender gatherNewsSender;

    @Override
    public ReturnT<String> execute(String step) {
        XxlJobLogger.log("XXL-JOB, 开始采集");

        try {
            startGather(step);
        } catch (Exception e) {
            e.printStackTrace();
            XxlJobLogger.log("XXL-JOB, " + e.getMessage());
            return FAIL;
        }
        XxlJobLogger.log("XXL-JOB, 完成采集");

        return SUCCESS;
    }

    private void startGather(String step) {
        int stepDays = 0;
        if (!Strings.isNullOrEmpty(step)) {
            try {
                stepDays = Integer.parseInt(step);
            } catch (NumberFormatException ignore) {

            }
        }
        int finalStepDays = stepDays;
        new XxlCrawler.Builder()
                .setUrls("https://news.zhibo8.cc/nba/more.htm")
                .setAllowSpread(true)
                .setWhiteUrlRegexs("https://news.zhibo8.cc/nba/.*/.*.htm")
                .setThreadCount(10)
                .setPauseMillis(1000)
                .setTimeoutMillis(5000)
                .setPageParser(new PageParser<GatherNewsVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, GatherNewsVo vo) {
                        // 解析封装 PageVo 对象

                        LocalDateTime createLocalDateTime = LocalDateTime.ofInstant(vo.getCreateTime().toInstant(), ZoneId.systemDefault());
                        LocalDateTime plusDays = createLocalDateTime.plusDays(finalStepDays);

                        if (plusDays.compareTo(LocalDateTime.now()) >= 0) {
                            String pageUrl = html.baseUri();
                            vo.setUrl(pageUrl);
                            gatherNewsSender.send(vo);
                        }

                    }
                }).build().start(true);
    }
}
