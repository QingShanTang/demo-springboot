package org.qingshan.async.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -715567008841111567L;
    private String id;
    private Integer age;
    private Date birthday;
    private String threadName;

    public User(String id, Integer age, Date birthday, String threadName) {
        this.id = id;
        this.age = age;
        this.birthday = birthday;
        this.threadName = threadName;
    }

    public User() {
    }
}
