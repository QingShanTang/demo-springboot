package org.qingshan.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ScheduleConfig {

    //实现多线程定时任务,如果没有,那么定时任务都是单线程执行
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskExecutor = new ThreadPoolTaskScheduler();
        taskExecutor.setPoolSize(5);
        taskExecutor.setThreadNamePrefix("taskScheduler-");
        return taskExecutor;
    }
}
