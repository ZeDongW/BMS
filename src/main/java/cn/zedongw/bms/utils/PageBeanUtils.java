package cn.zedongw.bms.utils;

import cn.zedongw.bms.entity.PageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PageBeanUtils
 * @Description: 分页查询工具
 * @Author ZeDongW
 * @Date 2020/3/30 0030 15:59
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public class PageBeanUtils {
    /**
     * 封装分页实体
     *
     * @param req
     * @param pb1
     * @param pb2
     * @param <T>
     */
    public static <T> void initPageBean(HttpServletRequest req, PageBean<T> pb1, PageBean<T> pb2) {
        //获取当前页
        String currPage = req.getParameter("currPage");
        //获取查询行数
        String pageCount = req.getParameter("pageCount");

        //查询当前页为空
        if (currPage == null || "".equals(currPage.trim())) {
            //第一次查询，查询页面数设为1
            if (pb2 == null) {
                currPage = "1";
            } else {
                currPage = String.valueOf(pb2.getCurrPage());
            }
        }

        //查询行数为空
        if (pageCount == null || "".equals(pageCount.trim())) {
            //第一次查询，默认设置查询5条数据
            if (pb2 == null) {
                pageCount = "5";
            } else {
                pageCount = String.valueOf(pb2.getPageCount());
            }
        }

        //设置查询属性
        pb1.setPageCount(Integer.parseInt(pageCount));
        pb1.setCurrPage(Integer.parseInt(currPage));
    }
}
