package org.qingshan.mybatis.dao;

import org.qingshan.mybatis.dto.StudentPagingParam;
import org.qingshan.mybatis.dto.StudentPagingResult;

import java.util.List;

public interface StudentDao {
    List<StudentPagingResult> pagingStudent(StudentPagingParam param);
}
