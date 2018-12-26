package com.tryndamere.zhibo8.trynbusiness.business.impl;

import com.tryndamere.zhibo8.trynbusiness.business.NewsPageBusiness;
import com.tryndamere.zhibo8.trynmodel.entity.NewsPage;
import com.tryndamere.zhibo8.trynpersistent.repository.NewsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dave on 2018/12/26
 * Describes
 */
@Service
public class NewsPageBusinessImpl implements NewsPageBusiness {
    @Autowired
    private NewsPageRepository newsPageRepository;
    @Override
    public void saveBatch(List<NewsPage> newsPages) {
        newsPageRepository.saveAll(newsPages);
    }
}
