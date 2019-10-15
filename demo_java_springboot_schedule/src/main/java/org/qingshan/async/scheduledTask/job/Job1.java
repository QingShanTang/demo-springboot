package org.qingshan.async.scheduledTask.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

@Slf4j
@Component
public class Job1 extends DefaultJob {
    @Override
    public void doIt() {
        log.info("job1运行线程是:" + Thread.currentThread().getName());
        log.info("job1 运行中。。。");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
