package com.tryndamere.zhibo8.harvest.vo;

import com.google.common.base.Strings;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Dave
 * @Date 2019/5/11
 * @Description
 */

@Data
@Accessors(chain = true)
@PageSelect(cssQuery = "body")
public class GatherNewsVo implements Serializable {

    @PageFieldSelect(cssQuery = ".blog-heading .title")
    private String url;
    @PageFieldSelect(cssQuery = "#main > div.box.margin_top_10 > div.title > h1")
    private String title;

    @PageFieldSelect(cssQuery = "#main > div.box.margin_top_10 > div.title > span",
            selectType = XxlCrawlerConf.SelectType.TEXT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @PageFieldSelect(cssQuery = "#signals")
    private String content;

    @PageFieldSelect(cssQuery = "#signals > p:last-child")
    private String reporterName;

    @PageFieldSelect(cssQuery = "#main > div.box.margin_top_10 > div.title > span > a:nth-child(1)")
    private String origin;

    public String getReporterName() {
        if (!Strings.isNullOrEmpty(reporterName)) {
            return reporterName.replaceAll("[（）]", "");
        }
        return this.reporterName;
    }
}
