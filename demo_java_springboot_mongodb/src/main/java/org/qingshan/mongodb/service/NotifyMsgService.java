package org.qingshan.mongodb.service;

import org.qingshan.mongodb.pojo.NotifyMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotifyMsgService {
    NotifyMsg saveNotifyMsg(NotifyMsg msg);

    NotifyMsg findNotifyMsgByNo(String notifyNo);

    List<NotifyMsg> findNotifyMsgByDate(String notifyDate);

    NotifyMsg delNotifyMsgById(String id);
}
