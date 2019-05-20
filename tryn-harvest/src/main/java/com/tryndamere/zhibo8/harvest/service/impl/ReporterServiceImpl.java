package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import com.tryndamere.zhibo8.harvest.entity.Reporter;
import com.tryndamere.zhibo8.harvest.mapper.ReporterMapper;
import com.tryndamere.zhibo8.harvest.service.IReporterService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dave
 * @since 2019-05-15
 */
@Service
public class ReporterServiceImpl extends ServiceImpl<ReporterMapper, Reporter> implements IReporterService {

    @Override
    public Reporter queryRepoterByName(String name) {

        return this.getOne(new QueryWrapper<Reporter>()
                .lambda()
                .eq(Reporter::getName, name));
    }

    @Override
    public Long saveRepoter(String reporterName) {

        if (Strings.isNullOrEmpty(reporterName)) {
            return 1L;
        }

        long reporterId;
        Reporter repoter = this.queryRepoterByName(reporterName);
        if (repoter != null) {
            return repoter.getId();
        } else {
            reporterId = IdWorker.getId();
            Reporter repoter1 = new Reporter()
                    .setId(reporterId)
                    .setName(reporterName);
            this.save(repoter1);
        }
        return reporterId;
    }
}
