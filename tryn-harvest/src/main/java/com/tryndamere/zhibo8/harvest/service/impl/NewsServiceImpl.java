package com.tryndamere.zhibo8.harvest.service.impl;

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

    }

    private void setData(GatherNewsVo vo) {
        Repoter reporter = new Repoter()
                .setName(vo.getReporterName());

        long reporterId;

        Repoter repoter = repoterService.queryRepoterByName(reporter.getName());
        if (repoter == null) {

            reporterId = IdWorker.getId();
            new Repoter()
                    .setId(reporterId)
                    .setName(reporter.getName());
            repoterService.save(reporter);
        } else {
            reporterId = repoter.getId();
        }


        News news = new News()
                .setContent(vo.getContent())
                .setReporterId(reporterId)
                .setCreateTime(LocalDateTime.ofInstant(vo.getCreateTime().toInstant(), ZoneId.systemDefault()))
                .setOrigin(vo.getOrigin())
                .setTitle(vo.getTitle());


    }
}
