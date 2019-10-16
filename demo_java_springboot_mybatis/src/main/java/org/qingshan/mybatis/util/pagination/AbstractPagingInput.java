package org.qingshan.mybatis.util.pagination;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractPagingInput implements Serializable {

    private SimplePageInfo pageInfo;

    private PaginationOrder order;

}