package com.tryndamere.zhibo8.trynbusiness.business.impl;

import com.tryndamere.zhibo8.trynbusiness.business.NewsPageBusiness;
import com.tryndamere.zhibo8.trynmodel.entity.NewsPage;
import com.tryndamere.zhibo8.trynpersistent.repository.NewsPageRepository;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.elasticsearch.index.search.QueryParserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
        //去重
        for (int i = 0; i < newsPages.size(); i++) {
            NewsPage newsPage = newsPages.get(i);
            String escape = QueryParser.escape(newsPage.getUrl());
            int count= newsPageRepository.countByUrl(escape);
            if(count>0){
                newsPages.remove(i);
            }

        }

        newsPageRepository.saveAll(newsPages);
    }
}
