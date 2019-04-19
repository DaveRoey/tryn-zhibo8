package com.trydamere.zhibo8.tryngateway.config;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author Dave
 * @Date 2019/4/7
 * @Description
 */
public class SentinelNacosConfig {
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosServer;
    @Value("${spring.cloud.sentinel.config.group}")
    private String groupId;
    @Value("${spring.cloud.sentinel.config.data-id}")
    private String dataId;

    private void init() {
        Converter<String, List<FlowRule>> parser = source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>() {});
        ReadableDataSource<String, List<FlowRule>> nacosDataSource = new NacosDataSource<>(nacosServer, groupId, dataId, parser);
        FlowRuleManager.register2Property(nacosDataSource.getProperty());
    }

    @Bean
    public String readSentinelStrategy(){
        init();
        return "create sentinel strategy";
    }

    public static void main(String[] args) {
        final String rule = "[\n"
                + "  {\n"
                + "    \"resource\": \"TestResource\",\n"
                + "    \"controlBehavior\": 0,\n"
                + "    \"count\": 5.0,\n"
                + "    \"grade\": 1,\n"
                + "    \"limitApp\": \"default\",\n"
                + "    \"strategy\": 0\n"
                + "  }\n"
                + "]";
        System.out.println(rule);
    }
}
