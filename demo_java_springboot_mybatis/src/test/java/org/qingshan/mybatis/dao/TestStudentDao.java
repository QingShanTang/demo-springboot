package org.qingshan.mybatis.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.qingshan.mybatis.MybatisApplication;
import org.qingshan.mybatis.mapper.StudentMapper;
import org.qingshan.mybatis.pojo.StudentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestStudentDao {

    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void testSelectAll(){
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        System.out.println(studentMapper.selectByExample(studentExample));
    }
}
