package org.qingshan.async.scheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.qingshan.async.props.ScheduledProps;
import org.qingshan.async.scheduledTask.job.Job1;
import org.qingshan.async.scheduledTask.job.Job2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TaskService {

    @Autowired
    private ScheduledProps scheduledProps;
    @Autowired
    private Job1 job1;
    @Autowired
    private Job2 job2;

    @Scheduled(cron = "${scheduled.job1Timer}")
    public void runJob1() {
        log.info(">>>>Start run schedule --> job1<<<<");
        job1.runJob(scheduledProps.getEnabled(), scheduledProps.getJob1Enabled());
        log.info(">>>>End run schedule job1<<<<");
    }

    @Scheduled(cron = "${scheduled.job2Timer}")
    public void runJob2() {
        log.info(">>>>Start run schedule --> job2<<<<");
        job2.runJob(scheduledProps.getEnabled(), scheduledProps.getJob2Enabled());
        log.info(">>>>End run schedule job2<<<<");
    }
}
