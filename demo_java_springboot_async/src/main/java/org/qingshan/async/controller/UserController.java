package org.qingshan.async.controller;

import lombok.extern.slf4j.Slf4j;
import org.qingshan.async.pojo.User;
import org.qingshan.async.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    static Map<String, Future> futureMap;

    static {
        futureMap = new HashMap<>();
    }

    @Autowired
    private UserService userService;

    /**
     * 没有返回值
     */
    @PostMapping(value = "/no/return")
    public void noReturn() throws InterruptedException {
        log.info("调用开始。。。");
        userService.printUser(new User("1", 12, new Date(), null));
        log.info("调用结束。。。");
    }


    /**
     * 有返回值
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @PostMapping(value = "/have/return")
    public void haveReturn() throws InterruptedException, ExecutionException {
        log.info("调用开始。。。");
        Future<User> future = userService.printUserReturn(new User("1", 12, new Date(), null));
        futureMap.put(UUID.randomUUID().toString(), future);
        log.info("调用结束。。。");
    }

}
