package org.qingshan.mongodb.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "tbl_notify_msg")
public class NotifyMsg {
    @Id
    private String id;

    @Field("notify_type")  //@Field相当于字段属性映射
    private String notifyType;

    @Field("notify_no")
    private String notifyNo;

    @Field("notify_date")
    private String notifyDate;

    @Field("notify_msg")
    private String notifyMsg;

    @Field("create_date")
    private Date createDate;
}
