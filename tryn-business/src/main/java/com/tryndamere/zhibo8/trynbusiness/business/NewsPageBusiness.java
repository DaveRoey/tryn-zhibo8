package com.tryndamere.zhibo8.trynbusiness.business;

import com.tryndamere.zhibo8.trynmodel.entity.NewsPage;

import java.util.List;

/**
 * Created by Dave on 2018/12/26
 * Describes
 */
public interface NewsPageBusiness {
    void saveBatch(List<NewsPage> newsPages);
}
