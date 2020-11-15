package com.zx.pojo;

import java.util.List;

public class Page<T> {
    private  static final Integer PAGE_SIZE =4;
    //当前页码数
    private Integer pageNo;

    //当前页显示数量
    private Integer pageSize = PAGE_SIZE;

    //总页码
    private Integer pageTotal;

    //总记录数
    private Integer pagePotalCount;


    //当前页数据
    private List<T> items;
    //分页条url client/bookServlet or  manager/bookServlet
    private String url;


    public Page() {
    }

    public Page(Integer pageNo, Integer pageSize, Integer pageTotal, Integer pagePotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.pagePotalCount = pagePotalCount;
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPagePotalCount() {
        return pagePotalCount;
    }

    public void setPagePotalCount(Integer pagePotalCount) {
        this.pagePotalCount = pagePotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {

        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", pagePotalCount=" + pagePotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
