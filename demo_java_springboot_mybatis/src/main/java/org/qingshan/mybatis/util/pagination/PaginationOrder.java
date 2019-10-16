package org.qingshan.mybatis.util.pagination;


import lombok.Data;

import java.io.Serializable;

@Data
public class PaginationOrder implements Serializable {

    public static final String SORT_VALUE_DESC = "desc";
    public static final String SORT_VALUE_ASC = "asc";

    private String columnName;

    private String sortValue;
}
