package com.tryndamere.zhibo8.harvest.mq;

import com.tryndamere.zhibo8.harvest.mq.listener.GatherCommentReceiver;
import com.tryndamere.zhibo8.harvest.mq.listener.GatherNewsReceiver;
import com.tryndamere.zhibo8.harvest.mq.listener.GatherNewsTotalReceiver;
import com.tryndamere.zhibo8.harvest.mq.listener.SaveCommentReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Dave
 * @Date 2019/1/27
 * @Description 消费者配置
 */
@Configuration
public class ConsumerConfig {
    private Logger logger = LoggerFactory.getLogger(ConsumerConfig.class);
    @Value("${rabbitmq.consumer.executor.size:100}")
    private int executeSize;
    @Value("${rabbitmq.consumer.concurrentConsumers:15}")
    private int concurrentConsumers;
    @Value("${rabbitmq.consumer.maxConcurrentConsumers:20}")
    private int maxConcurrentConsumers;


    /**
     * ContainerFactory配置
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        logger.info("config consumer TaskExecutor:{},ConcurrentConsumers:{},MaxConcurrentConsumers:{}", executeSize, concurrentConsumers, maxConcurrentConsumers);
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        ExecutorService service = Executors.newFixedThreadPool(executeSize);
        factory.setTaskExecutor(service);
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        factory.setPrefetchCount(5);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return factory;
    }


    /**
     * 设备上行状态redis消费receiver
     *
     * @return
     */
    @Bean
    public GatherNewsReceiver gatherNewsReceiver() {
        return new GatherNewsReceiver();
    }

    @Bean
    public GatherNewsTotalReceiver gatherNewsTotalReceiver() {
        return new GatherNewsTotalReceiver();
    }


    @Bean
    public GatherCommentReceiver gatherCommentReceiver() {
        return new GatherCommentReceiver();
    }

    @Bean
    SaveCommentReceiver saveCommentReceiver(){
        return new SaveCommentReceiver();
    }
}
