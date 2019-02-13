package com.tryndamere.zhibo8.trynpersistent.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.tryndamere.zhibo8.trynpersistent.custom.route.CommonDataBaseStrategy;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dave on 2019/2/13
 * Describes
 */
@Configuration
public class ShardingConfig {

    @Bean
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getEmployeeTableRuleConfiguration());
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(),shardingRuleConfig,new HashMap<>(),null);
    }



    TableRuleConfiguration getEmployeeTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration();
        result.setLogicTable("employee");
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("code",new CommonDataBaseStrategy()));
        result.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("id","ds$->{id % 2}"));
        result.setActualDataNodes("ds$->{0..1}.employee_$->{0..1}");
        return result;
    }


    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        for (int i=0;i<2;i++){
            String name="ds"+i;
            DataSource dataSource = createDataSource(name);
            result.put(name,dataSource);
        }
        return result;
    }


    private  javax.sql.DataSource createDataSource(final String dataSourceName) {
        DruidDataSource result=new DruidDataSource();
        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        result.setUrl(String.format("jdbc:mysql://118.24.0.221:3306/%s","shard_1"));
        result.setUsername("root");
        //sharding-jdbc默认以密码为空的root用户访问，如果修改了root用户的密码，这里修改为真实的密码即可；
        result.setPassword("123456");
        return result;
    }

    public static void main(String[] args) {
        Yaml yaml=new Yaml();
        yaml.loadAll()
    }

}
