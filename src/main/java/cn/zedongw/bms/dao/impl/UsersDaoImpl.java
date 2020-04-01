package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName UsersDaoImpl
 * @Description 用户操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:13
 * @modified By：
 */

public class UsersDaoImpl implements IUsersDao {

    /**
     * sql
     */
    private String sql;


    /**
     * 获取QueryRunner操作对象
     */
    private QueryRunner queryRunner = JdbcUtils.getQueryRunner();

    @Override
    public void add(Users user) {
        //新增用户sql
        sql = "insert into users(id, userName, passWord) values (?, ?, MD5(?))";
        try {
            //执行更新
            queryRunner.update(sql, user.getId(), user.getUserName(), user.getPassWord());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        //删除sql
        sql = "delete from users where id = ?";
        try {
            //执行更新
            queryRunner.update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Users user) {
        //修改sql
        sql = "update users set passWord = MD5(?) where id = ?";
        try {
            //执行更新
            queryRunner.update(sql, user.getPassWord(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findAll(PageBean<Users> pb) {
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

        //查找sql
        sql = "select * from users limit ?, ?";
        try {
            //执行查询
            pb.setPageData((ArrayList<Users>) queryRunner.query(sql, new BeanListHandler<>(Users.class), index, pageCount));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users findById(String id) {
        //查找sql
        sql = "select * from users where id = ?";
        try {
            //执行查询
            return queryRunner.query(sql, new BeanHandler<>(Users.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        //查找sql
        sql = "select * from users where userName = ? and passWord = MD5(?)";
        try {
            //执行查询
            return queryRunner.query(sql, new BeanHandler<>(Users.class), userName, passWord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean userNameExists(String userName) {
        //查找sql
        sql = "select count(1) from users where userName = ?";
        try {
            Long count = queryRunner.query(sql, new ScalarHandler<>(), userName);
            return count >= 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalCount() {
        //查询SQL
        sql = "select count(1) from users";
        try {
            //执行查询
            return queryRunner.query(sql, new ScalarHandler<Long>()).intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
