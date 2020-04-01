package cn.zedongw.bms.entity;

import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName PageBean
 * @Description 分页查询封装数据
 * @Version 1.0
 * @date ：Created in 2019/7/11 18:45
 * @modified By：
 */

public class PageBean<T> {

    /**
     * 当前页面
     */
    private int currPage;

    /**
     * 每页显示记录数
     */
    private int pageCount;

    /**
     * 查询总记录数
     */
    private int totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 分页查询的数据
     */
    private ArrayList<T> pageData;

    public PageBean() {
    }

    public PageBean(int currPage, int pageCount, int totalCount, int totalPage, ArrayList<T> pageData) {
        this.currPage = currPage;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageData = pageData;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        if (totalCount % pageCount == 0) {
            this.totalPage = totalCount / pageCount;
        } else {
            this.totalPage = totalCount / pageCount + 1;
        }
    }

    public ArrayList<T> getPageData() {
        return pageData;
    }

    public void setPageData(ArrayList<T> pageData) {
        this.pageData = pageData;
    }
}
