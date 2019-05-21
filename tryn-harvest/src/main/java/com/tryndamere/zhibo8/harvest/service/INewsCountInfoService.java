package com.tryndamere.zhibo8.harvest.service;

import com.tryndamere.zhibo8.harvest.entity.NewsCountInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tryndamere.zhibo8.harvest.vo.CommentPageVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dave
 * @since 2019-05-20
 */
public interface INewsCountInfoService extends IService<NewsCountInfo> {

    void saveNewsCountInfo(CommentPageVo vo, Long newsId);

    /**
     * 根据新闻ID查询记录数信息
     *
     * @param newsId
     */
    NewsCountInfo queryNewsCountInfoByNewsId(Long newsId);
}
