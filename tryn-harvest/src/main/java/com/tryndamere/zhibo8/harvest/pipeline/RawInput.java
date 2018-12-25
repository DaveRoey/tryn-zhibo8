package com.tryndamere.zhibo8.harvest.pipeline;

public class RawInput<T> extends DataFlow<T> {

    public int getLeftCount() {
        return queue.size();
    }

    @Override
    public T poll() {
        return queue.poll();
    }

}
