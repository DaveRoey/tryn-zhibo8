package com.tryndamere.zhibo8.harvest.processor;

import com.tryndamere.zhibo8.harvest.pipeline.DataProcessor;
import com.tryndamere.zhibo8.harvest.pipeline.HashSetDuplicateRemover;
import com.tryndamere.zhibo8.trynmodel.pojo.Employee;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Json;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 从原始数据生成满足 Elasticsearch 格式的 json 数据
 */
public class ZhihuFolloweeDataProcessor implements DataProcessor<File,Employee> {

    private HashSetDuplicateRemover<String> duplicateRemover = new HashSetDuplicateRemover<>();

    @Override
    public List<Employee> process(File inItem) {
        String s = MemberURLTokenGenerator.readFollowees(inItem);
        List<Employee> documents = null;

        if (!StringUtils.isEmpty(s)) {
            documents = new ArrayList<>(20);
            Json json = new Json(s);
            List<String> users = json.jsonPath("$.data[*].[*]").all();
            List<String> ids = json.jsonPath("$.data[*].id").all();
            int i = 0;
            for (String id : ids) {
                if (!duplicateRemover.isDuplicate(id)) {
                    documents.add(new Employee(1L,users.get(0),new Date()));
                }
                i++;
            }
        }
        return documents;
    }

}
