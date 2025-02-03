package com.wuzeyu.gateway.center.infrastructure.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author
 */
public class OperationRequest<T> {

    private int pageStart = 0;  //开始 limit 第一个参数

    private int pageEnd = 0;    //结束 limit 第二个参数

    private int pageIndex;

    private int pageSize;

    private T data;

    public OperationRequest() {}

    public OperationRequest(String page, String rows) {
        this.pageIndex = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        this.pageSize = StringUtils.isEmpty(page) ? 10 : Integer.parseInt(rows);
        if (0 == this.pageIndex) {
            this.pageIndex = 1;
        }
        this.pageStart = (this.pageIndex - 1) * this.pageSize;
        this.pageEnd = this.pageSize;
    }

    public OperationRequest(int page, int rows) {
        this.pageIndex = 0 == page ? 1 : page;
        this.pageSize = 0 == rows ? 10 : rows;
        this.pageStart = (this.pageIndex - 1) * this.pageSize;
        this.pageEnd = this.pageSize;
    }

    public void setPage(String page, String rows) {
        this.pageIndex = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
        this.pageSize = StringUtils.isEmpty(page) ? 10 : Integer.parseInt(rows);
        if (0 == this.pageIndex) {
            this.pageIndex = 1;
        }
        this.pageStart = (this.pageIndex - 1) * this.pageSize;
        this.pageEnd = this.pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if (data instanceof String) {
            String str = (String) data;
            if (StringUtils.isEmpty(str)){
                data = null;
            }
        }
        this.data = data;
    }

}
