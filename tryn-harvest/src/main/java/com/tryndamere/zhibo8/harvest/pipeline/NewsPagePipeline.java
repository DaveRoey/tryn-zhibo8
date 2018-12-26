package com.tryndamere.zhibo8.harvest.pipeline;

import com.tryndamere.zhibo8.harvest.processor.NewsPageProcessor;
import com.tryndamere.zhibo8.trynbusiness.business.impl.NewsPageBusinessImpl;
import com.tryndamere.zhibo8.trynmodel.entity.NewsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * Created by Dave on 2018/12/26
 * Describes
 * @author Dave
 */
@Component
public class NewsPagePipeline implements Pipeline {
    @Autowired
    private NewsPageBusinessImpl newsPageBusiness;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<NewsPage> morePages=resultItems.get("more");
        newsPageBusiness.saveBatch(morePages);

    }
}
