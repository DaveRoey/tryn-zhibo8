package com.tryndamere.zhibo8.harvest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tryndamere.zhibo8.harvest.entity.NewsCountInfo;
import com.tryndamere.zhibo8.harvest.mapper.NewsCountInfoMapper;
import com.tryndamere.zhibo8.harvest.service.INewsCountInfoService;
import com.tryndamere.zhibo8.harvest.vo.CommentPageVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dave
 * @since 2019-05-20
 */
@Service
public class NewsCountInfoServiceImpl extends ServiceImpl<NewsCountInfoMapper, NewsCountInfo> implements INewsCountInfoService {

    @Override
    public void saveNewsCountInfo(CommentPageVo vo, Long newsId) {
        NewsCountInfo newsCountInfo = queryNewsCountInfoByNewsId(newsId);

        if (null == newsCountInfo) {
            newsCountInfo = new NewsCountInfo()
                    .setAllNum(vo.getAllNum())
                    .setAllShortNum(vo.getAllShortNum())
                    .setHotNum(vo.getHotNum())
                    .setNewsId(newsId)
                    .setRootNormalNum(vo.getRootNormalNum())
                    .setRootNum(vo.getRootNum());

            this.save(newsCountInfo);
        }

    }

    @Override
    public NewsCountInfo queryNewsCountInfoByNewsId(Long newsId) {
        return this.getOne(new LambdaQueryWrapper<NewsCountInfo>()
                .eq(NewsCountInfo::getNewsId, newsId));
    }


}
