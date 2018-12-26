package com.tryndamere.zhibo8.trynpersistent.repository;

import com.tryndamere.zhibo8.trynmodel.entity.NewsPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Dave on 2018/12/26
 * Describes
 */
@Component
public interface NewsPageRepository extends CrudRepository<NewsPage,String> {

}
