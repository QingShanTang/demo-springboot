package org.qingshan.mybatis;

import com.sun.org.apache.bcel.internal.classfile.ConstantValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


//综合来看,使用jasypt加密配置文件最方便
@SpringBootApplication
@ServletComponentScan
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
