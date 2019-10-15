package org.qingshan.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableScheduling  //注解方式开启spring内置定时任务
public class ScheduleConfig {

    /*spring内置定时任务默认是单线程执行的
    如果需要多个job异步执行,可以配置线程池,有两种方法,一种是实现SchedulingConfigurer,通过ScheduledTaskRegistrar配置。
    另外一种是通过ThreadPoolTaskScheduler配置,可以参考https://blog.csdn.net/zero__007/article/details/81042801
    要注意,sping的定时任务多线程是针对多个job来说的,对于单一job自身依然是串行的。如果需要单个job也异步，那么可以考虑@Async直接启动异步执行*/
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(100);
        taskScheduler.setThreadNamePrefix("taskScheduler-");
        return taskScheduler;
    }

}
