package com.tryndamere.zhibo8.harvest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tryndamere.zhibo8.harvest.entity.Reporter;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dave
 * @since 2019-05-16
 */
public interface IReporterService extends IService<Reporter> {

    Reporter queryRepoterByName(String name);

    Long saveRepoter(String reporterName);
}
