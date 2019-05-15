package com.tryndamere.zhibo8.harvest.service;

import com.tryndamere.zhibo8.harvest.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tryndamere.zhibo8.harvest.vo.GatherNewsVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dave
 * @since 2019-05-15
 */
public interface INewsService extends IService<News> {

    /**
     * 保存新闻信息
     *
     * @param param
     */
    void saveNews(GatherNewsVo param);

    News queryNewsByUrl(String url);

}
