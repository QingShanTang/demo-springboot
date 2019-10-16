package org.qingshan.mongodb.controller;

import lombok.extern.slf4j.Slf4j;
import org.qingshan.mongodb.pojo.NotifyMsg;
import org.qingshan.mongodb.service.NotifyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/template")
public class MongoTemplateController {

    @Autowired
    private NotifyMsgService notifyMsgService;

    @PostMapping("/add")
    public NotifyMsg add(
            @RequestBody NotifyMsg msg
    ) {
        log.info("mongoTemplate方式新增：{}", msg);
        return notifyMsgService.saveNotifyMsg(msg);
    }

    @PostMapping("del/{id}")
    public NotifyMsg del(
            @PathVariable String id
    ) {
        log.info("mongoTemplate方式删除：{}", id);
        return notifyMsgService.delNotifyMsgById(id);
    }

    @GetMapping("/find/{no}")
    public NotifyMsg findNotifyMsgByNo(
            @PathVariable String no
    ) {
        log.info("mongoTemplate方式查找：notifyNo-{}", no);
        return notifyMsgService.findNotifyMsgByNo(no);
    }

    @GetMapping("/find/list/{date}")
    public List<NotifyMsg> findNotifyMsgByDate(
            @PathVariable String date
    ) {
        log.info("mongoTemplate方式查找：notifyDate-{}", date);
        return notifyMsgService.findNotifyMsgByDate(date);
    }
}
