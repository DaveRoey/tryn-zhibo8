package com.tryndamere.zhibo8.harvest.pipeline;

public interface DuplicateRemover<ID> {

    boolean isDuplicate(ID id);

    void resetDuplicateCheck();

    int getTotalCount();

}
