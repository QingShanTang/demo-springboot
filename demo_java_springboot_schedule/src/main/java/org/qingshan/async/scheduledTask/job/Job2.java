package org.qingshan.async.scheduledTask.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Job2 extends DefaultJob {

    @Override
    public void doIt() {
        log.info("job2运行线程是:" + Thread.currentThread().getName());
        log.info("job2 运行中。。。");
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
