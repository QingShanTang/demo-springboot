package org.qingshan.mybatis.util.pagination;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class PaginationUtil {
    public static PagingOutput processPaging(AbstractPagingInput pagingInput, PaginationCallback<?> callback) {
        //如果没有分页信息，则默认是第一页，每页取10条
        if (pagingInput != null && pagingInput.getPageInfo() != null) {
            if (pagingInput.getPageInfo().getCurrent() == null) {
                pagingInput.getPageInfo().setCurrent(1);
            }
            if (pagingInput.getPageInfo().getPageSize() == null || pagingInput.getPageInfo().getPageSize() == 0) {
                pagingInput.getPageInfo().setPageSize(10);
            }
            PageHelper.startPage(pagingInput.getPageInfo().getCurrent(), pagingInput.getPageInfo().getPageSize());
        } else if (pagingInput != null) {
            SimplePageInfo pageinfo = new SimplePageInfo();
            pageinfo.setCurrent(1);
            pageinfo.setPageSize(10);
            PageHelper.startPage(pagingInput.getPageInfo().getCurrent(), pagingInput.getPageInfo().getPageSize());
        }

        if (pagingInput != null && pagingInput.getOrder() != null) {
            String orderBy = null;
            if (StringUtils.isNotBlank(pagingInput.getOrder().getColumnName())) {
                orderBy = pagingInput.getOrder().getColumnName();
                if (StringUtils.isNotBlank(pagingInput.getOrder().getSortValue())) {
                    orderBy += (" " + pagingInput.getOrder().getSortValue());
                }

            }
            if (StringUtils.isNotBlank(orderBy)) {
                PageHelper.orderBy(orderBy);
            } else {
                //TODO need to check mult tables case
                //PageHelper.orderBy("UPDATE_DATE DESC");
            }
        }
        List<?> realList = callback.doProcess();
        PagingOutput rt = new PagingOutput();
        if (pagingInput != null && pagingInput.getPageInfo() != null) {
            PageInfo<?> newPageInfo = new PageInfo<>(realList);
            SimplePageInfo simplePageInfo = new SimplePageInfo();
            simplePageInfo.setCurrent(newPageInfo.getPageNum());
            simplePageInfo.setPageSize(newPageInfo.getPageSize());
            simplePageInfo.setMaxPage(newPageInfo.getPages());
            simplePageInfo.setTotal(newPageInfo.getTotal());
            rt.setPageInfo(simplePageInfo);
        }
        List<Object> data = new ArrayList<Object>();
        if (realList != null && !realList.isEmpty()) {
            for (int i = 0; i < realList.size(); i++) {
                Object item = realList.get(i);
                data.add(item);
            }
        }
        rt.setList(data);
        return rt;
    }
}