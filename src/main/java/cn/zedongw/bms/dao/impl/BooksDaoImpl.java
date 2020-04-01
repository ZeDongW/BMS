package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName BooksDaoImpl
 * @Description 书本数据操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:16
 * @modified By：
 */

public class BooksDaoImpl implements IBooksDao {

    /**
     * sql
     */
    private String sql;

    /**
     * 获取QueryRunner操作对象
     */
    private QueryRunner queryRunner = JdbcUtils.getQueryRunner();

    @Override
    public void add(Books book) {
        //SQL
        sql = "insert into books (id, bookName, bookAuthor, publisher, price, bookNum, publishDate) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            //执行更新
            queryRunner.update(sql, book.getId(), book.getBookName(), book.getBookAuthor(), book.getPublisher(), book.getPrice(),
                    book.getBookNum(), book.getPublishDate());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        //SQL
        sql = "delete from books where id = ?";
        try {
            //执行更新
            queryRunner.update(sql, id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Books book) {
        //SQL
        sql = "update books set bookName = ?, bookAuthor = ?, publisher = ?, price = ?, " +
                "bookNum = ?, publishDate = ? where id = ?";
        try {
            //执行更新
            queryRunner.update(sql, book.getBookName(), book.getBookAuthor(), book.getPublisher(),
                    book.getPrice(), book.getBookNum(), book.getPublishDate(), book.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll(PageBean<Books> pb) {
        //获取总记录数
        pb.setTotalCount(getTotalCount());

        //初始化总页数
        pb.setTotalPage();

        //设置当前页数
        if (pb.getCurrPage() > pb.getTotalPage()) {
            //当前查询页大于当前页数，设置为最大页数
            pb.setCurrPage(pb.getTotalPage());
        }

        if (pb.getCurrPage() < 1) {
            //当前查询页小于1，设置为1
            pb.setCurrPage(1);
        }

        //当前页数
        int currPage = pb.getCurrPage();

        //查询行数
        int pageCount = pb.getPageCount();

        //查询起始行
        int index = (currPage - 1) * pageCount;

        //SQL
        sql = "select * from books limit ?, ?";
        try {
            //执行查询
            pb.setPageData((ArrayList<Books>) queryRunner.query(sql, new BeanListHandler<>(Books.class), index, pageCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Books findById(String id) {
        //SQL
        sql = "select * from books where id = ?";
        try {
            //执行查询
            return queryRunner.query(sql, new BeanHandler<>(Books.class), id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 获取总记录数
     *
     * @throws
     * @methodName: getTotalCount
     * @return: int
     * @author: ZeDongW
     * @date: 2020/3/30 0030 13:18
     */
    @Override
    public int getTotalCount() {
        //SQL
        sql = "select count(1) from books";
        try {
            //执行查询
            return queryRunner.query(sql, new ScalarHandler<Long>()).intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
