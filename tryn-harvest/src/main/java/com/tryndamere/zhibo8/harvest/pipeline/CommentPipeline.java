package com.tryndamere.zhibo8.harvest.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Author Dave
 * @Date 2018/12/25
 * @Description
 */
public class CommentPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Object data = resultItems.get("data");
        System.out.println(data);
    }
}
