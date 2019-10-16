package org.qingshan.mongodb.service.impl;

import org.qingshan.mongodb.pojo.NotifyMsg;
import org.qingshan.mongodb.service.NotifyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyMsgServiceImpl implements NotifyMsgService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public NotifyMsg saveNotifyMsg(NotifyMsg msg) {
//        mongoTemplate.insert(msg);
        mongoTemplate.save(msg);
        return null;
    }

    @Override
    public NotifyMsg findNotifyMsgByNo(String notifyNo) {
        Query query = new Query(Criteria.where("notify_no").is(notifyNo));
        return mongoTemplate.findOne(query, NotifyMsg.class);
    }

    @Override
    public List<NotifyMsg> findNotifyMsgByDate(String notifyDate) {
        Query query = new Query(Criteria.where("notify_date").is(notifyDate));
        return mongoTemplate.find(query, NotifyMsg.class);
    }

    @Override
    public NotifyMsg delNotifyMsgById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query, NotifyMsg.class);
    }
}
