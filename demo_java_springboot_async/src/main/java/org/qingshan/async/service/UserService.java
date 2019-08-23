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
    public void printUser(User user) {
        log.info("user:" + user);
    }

    @Async
    public Future<User> printUserReturn(User user) {
        log.info("user:" + user);
        return new AsyncResult<>(user);
    }

}
