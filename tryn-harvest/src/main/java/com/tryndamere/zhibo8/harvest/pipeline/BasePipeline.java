package com.tryndamere.zhibo8.harvest.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @Author Dave
 * @Date 2018/12/25
 * @Description
 */
public class BasePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> map = resultItems.getAll();

    }
}
