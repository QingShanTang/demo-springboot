package org.qingshan.async.scheduledTask.job;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public abstract class DefaultJob {

    public void runJob(String mainEnabled, String jobEnabled) {
        log.info("start run job...");
        if (!(Boolean.parseBoolean(mainEnabled) && Boolean.parseBoolean(jobEnabled))) {
            log.info("job scheduled not open!");
            return;
        }
        this.init();
        this.doIt();
        this.ending();
    }

    protected void doIt() {
    }

    public void init() {
        log.info(this.getClass().getSimpleName() + " job init time:" + new Date());
    }

    protected void ending() {
        log.info(this.getClass().getSimpleName() + " job ending time:" + new Date());
    }
}
