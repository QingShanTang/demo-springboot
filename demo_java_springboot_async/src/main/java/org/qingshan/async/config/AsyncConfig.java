package org.qingshan.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


/*线程池工作原理:

        如果当前运行的线程少于corePoolSize，则创建新线程来执行任务（需要获得全局锁）
        如果运行的线程等于或多于corePoolSize ,则将任务加入BlockingQueue(queueCapacity)
        如果无法将任务加入BlockingQueue(queueCapacity)(队列已满)，则创建新的线程来处理任务（需要获得全局锁）
        如果创建新线程将使当前运行的线程超出maxiumPoolSize，任务将被拒绝，并调用RejectedExecutionHandler.rejectedExecution()方法*/

/*如果不配置Executor,会打印log-->No task executor bean found for async processing: no bean of type TaskExecutor and no bean named 'taskExecutor' either
但是异步仍然会调用成功，因为之后会默认使用SimpleAsyncTaskExecutor,每次都重启一个新线程*/
@Configuration
@EnableAsync
public class AsyncConfig {
    //    核心线程数 -->线程池维护线程的最小数量
    private int corePoolSize = 3;
    //    最大线程数 -->线程池维护线程的最大数量
    private int maxPoolSize = 100;
    //    队列容量 -->队列是用来存放任务的。当线程池中有空闲线程时就回去任务队列中拿任务并处理
    private int queueCapacity = 4;
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
