package org.qingshan.async.scheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SimpleTask {

//    @Scheduled(cron = "0/1 * * * * ?")
//    public void task01() throws InterruptedException {
//        log.info(Thread.currentThread().getName());
//        TimeUnit.SECONDS.sleep(2);
//    }
//
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void task02() throws InterruptedException {
//        log.info(Thread.currentThread().getName());
//        TimeUnit.SECONDS.sleep(2);
//
//    }
//
//    @Scheduled(cron = "0/1 * * * * ?")
//    public void task03() throws InterruptedException {
//        log.info(Thread.currentThread().getName());
//        TimeUnit.SECONDS.sleep(2);
//
//    }
}
