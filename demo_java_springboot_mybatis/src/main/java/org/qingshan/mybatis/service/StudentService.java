package org.qingshan.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.qingshan.mybatis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;
    public void findStudent(){
        System.out.println(studentMapper.selectByPrimaryKey("1"));
    }
}
