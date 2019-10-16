package org.qingshan.mybatis.util.pagination;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PagingOutput<T> implements Serializable {

    private SimplePageInfo pageInfo;

    private List<T> list;

}