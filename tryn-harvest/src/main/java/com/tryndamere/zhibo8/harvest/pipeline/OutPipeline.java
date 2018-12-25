package com.tryndamere.zhibo8.harvest.pipeline;

import java.util.List;

public interface OutPipeline<T> {
    void process(T outItem);

    default void process(List<T> outItems) {
        outItems.forEach(this::process);
    }
}
