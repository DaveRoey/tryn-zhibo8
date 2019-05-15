package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.entity.Repoter;
import com.tryndamere.zhibo8.harvest.mapper.RepoterMapper;
import com.tryndamere.zhibo8.harvest.service.IRepoterService;
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
public class RepoterServiceImpl extends ServiceImpl<RepoterMapper, Repoter> implements IRepoterService {

    @Override
    public Repoter queryRepoterByName(String name) {

        return this.getOne(new QueryWrapper<Repoter>()
                .lambda()
                .eq(Repoter::getName, name));
    }
}
