package org.qingshan.mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.qingshan.mybatis.dto.StudentPagingParam;
import org.qingshan.mybatis.pojo.Student;
import org.qingshan.mybatis.service.StudentService;
import org.qingshan.mybatis.util.pagination.PagingOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * PageHelper原始使用
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/simple/paging")
    public PageInfo<Student> pagingStudentSimple(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
    ) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> listStudent = studentService.listStudent();
        PageInfo<Student> pageInfo = new PageInfo<>(listStudent);
        return pageInfo;
    }


/*    {
        "pageInfo":{
        "current":1,
                "pageSize":3
    },
        "order":{
        "columnName":"age",
                "sortValue":"ASC"
    },
        "name":"1"
    }*/

    /**
     * PageHelper封装使用
     *
     * @param param
     * @return
     */
    @PostMapping(value = "/package/paging")
    public PagingOutput pagingStudentPackage(
            @RequestBody StudentPagingParam param
    ) {
        PagingOutput out = studentService.paging(param);
        return out;
    }
}
