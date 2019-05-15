package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.entity.News;
import com.tryndamere.zhibo8.harvest.entity.Repoter;
import com.tryndamere.zhibo8.harvest.mapper.NewsMapper;
import com.tryndamere.zhibo8.harvest.service.INewsService;
import com.tryndamere.zhibo8.harvest.service.IRepoterService;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;
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

    @Autowired
    IRepoterService repoterService;

    @Override
    public void saveNews(GatherNewsVo param) {
        Long reporterId = repoterService.saveRepoter(param.getReporterName());
        News newsInfo = queryNewsByUrl(param.getUrl());
        if (null == newsInfo) {
            newsInfo = getNewsInfo(param, reporterId);
            this.save(newsInfo);
        }
    }

    @Override
    public News queryNewsByUrl(String url) {
        return this.getOne(new QueryWrapper<News>()
                .lambda()
                .eq(News::getUrl, url));
    }

    private News getNewsInfo(GatherNewsVo vo, Long repoterId) {
        return new News()
                .setContent(vo.getContent())
                .setReporterId(repoterId)
                .setCreateTime(LocalDateTime.ofInstant(vo.getCreateTime().toInstant(), ZoneId.systemDefault()))
                .setOrigin(vo.getOrigin())
                .setTitle(vo.getTitle());
    }

}
