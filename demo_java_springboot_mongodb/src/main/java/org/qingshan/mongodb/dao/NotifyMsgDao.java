package org.qingshan.mongodb.dao;

import org.qingshan.mongodb.pojo.NotifyMsg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

//我个人觉得repository类似于sql语句填空。sql模板给定，按照需求给sql模板填空。
public interface NotifyMsgDao extends MongoRepository<NotifyMsg, String> {

    /*
     * 根据消息号进行查询
     */
    NotifyMsg findByNotifyNo(String notifyNo);

    /**
     * 根据日期查询 自定义查询
     */
    //    这里使用字段名和属性名都可以,'?0'代表参数的索引值，从0开始。
    @Query("{'notifyDate':?0}")
    Page<NotifyMsg> queryBySql(String notifyDate, Pageable pageable);
}
