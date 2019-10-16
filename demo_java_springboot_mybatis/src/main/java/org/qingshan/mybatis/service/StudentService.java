package org.qingshan.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.qingshan.mybatis.dao.StudentDao;
import org.qingshan.mybatis.dto.StudentPagingParam;
import org.qingshan.mybatis.dto.StudentPagingResult;
import org.qingshan.mybatis.mapper.StudentMapper;
import org.qingshan.mybatis.pojo.Student;
import org.qingshan.mybatis.pojo.StudentExample;
import org.qingshan.mybatis.util.pagination.PaginationCallback;
import org.qingshan.mybatis.util.pagination.PaginationUtil;
import org.qingshan.mybatis.util.pagination.PagingOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentDao studentDao;

    public void findStudent() {
        System.out.println(studentMapper.selectByPrimaryKey("1"));
    }

    public List<Student> listStudent() {
        return studentMapper.selectByExample(new StudentExample());
    }

    public PagingOutput paging(StudentPagingParam param) {
        PagingOutput out = PaginationUtil.processPaging(param, new PaginationCallback<StudentPagingResult>() {
            @Override
            public List<StudentPagingResult> doProcess() {
                return studentDao.pagingStudent(param);
            }
        });
        return out;
    }
}
