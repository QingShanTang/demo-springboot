package org.qingshan.async.controller;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/print")
    public void printUser() {

        userService.printUser(new User("1", 12, new Date()));
    }

    @PostMapping(value = "/print/return")
    public void printUserReturn() throws ExecutionException, InterruptedException {

        Future<User> future = userService.printUserReturn(new User("1", 12, new Date()));
        User user = future.get();
        System.out.println(future.get());
    }


}
