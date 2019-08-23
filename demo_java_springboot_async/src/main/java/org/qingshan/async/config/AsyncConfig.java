package org.qingshan.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    //    核心线程数 -->线程池维护线程的最小数量
    private int corePoolSize = 10;
    //    最大线程数 -->线程池维护线程的最大数量
    private int maxPoolSize = 200;
    //    队列容量 -->队列是用来存放任务的。当线程池中有空闲线程时就回去任务队列中拿任务并处理
    private int queueCapacity = 1000;
    //    空闲线程的存活时间
    private int keepAliveSeconds = 60;
    //    拒绝策略
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("qingshan-");
        executor.setRejectedExecutionHandler(handler);
        executor.initialize();
        return executor;
    }
}
