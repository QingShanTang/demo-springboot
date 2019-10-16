package org.qingshan.mybatis.dto;

import lombok.Data;
import org.qingshan.mybatis.util.pagination.AbstractPagingInput;

@Data
public class StudentPagingParam extends AbstractPagingInput {
    private String name;

}
