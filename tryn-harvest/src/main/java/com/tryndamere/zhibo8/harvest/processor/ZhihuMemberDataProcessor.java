package com.tryndamere.zhibo8.harvest.processor;

import com.tryndamere.zhibo8.harvest.config.ZhihuConfiguration;
import com.tryndamere.zhibo8.harvest.pipeline.*;
import com.tryndamere.zhibo8.tryncommon.utils.FileHelper;
import com.tryndamere.zhibo8.trynmodel.pojo.Employee;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.selector.Json;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 从原始数据生成满足 Elasticsearch 格式的 json 数据
 */
public class ZhihuMemberDataProcessor implements DataProcessor<File, Employee> {

    private HashSetDuplicateRemover<String> duplicateRemover = new HashSetDuplicateRemover<>();

    @Override
    public List<Employee> process(File inItem) {
        String s = readMember(inItem);
        List<Employee> documents = null;

        if (!StringUtils.isEmpty(s)) {
            documents = new ArrayList<>(1);
            Json json = new Json(s);
            String id = json.jsonPath("$.id").get();
            if (!duplicateRemover.isDuplicate(id)) {
                documents.add(new Employee(1L,"aa",new Date()));
            }
        }
        return documents;
    }

    public static String readMember(File inItem) {
        List<String> followees = FileHelper.processFile(inItem, br -> {
            br.readLine();//pass first line
            String s = br.readLine();
            return Collections.singletonList(s);
        }).orElse(new ArrayList<>());

        return followees.size() == 0 ? null : followees.get(0);
    }

    public static void main(String[] args) {
        ZhihuConfiguration configuration = new ZhihuConfiguration();
        String folder = configuration.getMemberDataPath();
        DataProcessor<File, Employee> processor = new ZhihuMemberDataProcessor();
        ConsoleOutpipeline<Employee> outPipeline = new ConsoleOutpipeline<>();

        BaseAssembler.create(new FileRawInput(folder), processor)
                .addOutPipeline(i -> {
                }) // 需要打印时替换为 outPipeline
                .thread(10)
                .run();

        System.out.println("out sent :" + outPipeline.getCount());
    }

}
