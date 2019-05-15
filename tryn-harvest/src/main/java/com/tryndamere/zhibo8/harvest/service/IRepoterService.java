package com.tryndamere.zhibo8.harvest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tryndamere.zhibo8.harvest.entity.Repoter;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dave
 * @since 2019-05-15
 */
public interface IRepoterService extends IService<Repoter> {

    Repoter queryRepoterByName(String name);

    Long saveRepoter(String reporterName);
}
