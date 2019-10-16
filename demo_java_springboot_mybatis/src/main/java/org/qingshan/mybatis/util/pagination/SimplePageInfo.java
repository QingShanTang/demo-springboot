package org.qingshan.mybatis.util.pagination;


import lombok.Data;

import java.io.Serializable;

@Data
public class SimplePageInfo implements Serializable {

    private Integer current;

    private Integer pageSize;

    private Integer maxPage;

    private Long total;

}
