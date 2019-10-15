package org.qingshan.async.service;

import lombok.extern.slf4j.Slf4j;
import org.qingshan.async.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class UserService {

    @Async
    public void printUser(User user) throws InterruptedException {
        log.info("异步开启。。。");
        Thread.sleep(5 * 1000);
        user.setThreadName(Thread.currentThread().getName());
        log.info("user:" + user);
        log.info("异步结束。。。");
    }

    @Async
    public Future<User> printUserReturn(User user) throws InterruptedException {
        log.info("异步开启。。。");
        Thread.sleep(5 * 1000);
        user.setThreadName(Thread.currentThread().getName());
        log.info("user:" + user);
        log.info("异步结束。。。");
        return new AsyncResult<>(user);
    }

}
