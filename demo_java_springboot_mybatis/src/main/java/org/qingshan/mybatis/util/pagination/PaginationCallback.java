package org.qingshan.mybatis.util.pagination;

import java.util.List;

public interface PaginationCallback<T> {
    /***
     * 分页查询回调逻辑
     * @return
     */
    List<T> doProcess();
}
