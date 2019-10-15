package org.qingshan.mybatis.mapper;

import java.util.List;
import org.qingshan.mybatis.pojo.Student;
import org.qingshan.mybatis.pojo.StudentExample;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}