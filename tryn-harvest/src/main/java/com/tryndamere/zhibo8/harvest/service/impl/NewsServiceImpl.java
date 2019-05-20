package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.dto.NewsTotalDto;
import com.tryndamere.zhibo8.harvest.entity.News;
import com.tryndamere.zhibo8.harvest.mapper.NewsMapper;
import com.tryndamere.zhibo8.harvest.mq.sender.GatherNewsTotalSender;
import com.tryndamere.zhibo8.harvest.service.INewsService;
import com.tryndamere.zhibo8.harvest.service.IReporterService;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dave
 * @since 2019-05-15
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
    private Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    IReporterService reporterService;
    @Autowired
    GatherNewsTotalSender gatherNewsTotalSender;

    @Override
    public void saveNews(GatherNewsVo param) {
        Long reporterId = reporterService.saveRepoter(param.getReporterName());
        News newsInfo = queryNewsByUrl(param.getUrl());
        if (null == newsInfo) {
            newsInfo = getNewsInfo(param, reporterId);
            try {
                this.save(newsInfo);

                //采集新闻条数信息
                gatherNewsTotalSender.send(new NewsTotalDto()
                        .setNewsId(newsInfo.getId())
                        .setUrl(newsInfo.getUrl())
                        .setCreateDate(newsInfo.getCreateTime())
                        .setFileName(param.getFileName()));
            } catch (Exception e) {
                e.printStackTrace();
                log.info("save news exception {}", e.getMessage());
            }

        }

    }

    @Override
    public News queryNewsByUrl(String url) {
        return this.getOne(new QueryWrapper<News>()
                .lambda()
                .eq(News::getUrl, url));
    }

    private News getNewsInfo(GatherNewsVo vo, Long repoterId) {
        Long newsId = IdWorker.getId();
        return new News()
                .setId(newsId)
                .setContent(vo.getContent())
                .setUrl(vo.getUrl())
                .setReporterId(repoterId)
                .setCreateTime(vo.getCreateTime())
                .setOrigin(vo.getOrigin())
                .setTitle(vo.getTitle());
    }

}
