package org.qingshan.mybatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.qingshan.mybatis.MybatisApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestStudentService {
    @Autowired
    private StudentService studentService;

    @Test
    public void TestStudentService(){
        studentService.findStudent();
    }
}
