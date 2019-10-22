package org.qingshan.mybatis;

import org.jasypt.util.text.BasicTextEncryptor;

public class TestJasypt {
    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("qingshan");
        //要加密的数据（数据库的用户名或密码）
        String password = textEncryptor.encrypt("123456");
        System.out.println("password:"+password);
    }
}
