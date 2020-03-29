package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IUsersDao;
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

    public UsersDaoImpl() {
    }

    @Override
    public void add(Users user) {
        try {
            //新增用户sql
            sql = "insert into users(id, userName, passWord) values (?, ?, MD5(?))";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行更新
            queryRunner.update(sql, user.getId(), user.getUserName(), user.getPassWord());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            //删除sql
            sql = "delete from users where id = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行更新
            queryRunner.update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Users user) {
        try {
            //修改sql
            sql = "update users set passWord = MD5(?) where id = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行更新
            queryRunner.update(sql, user.getPassWord(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Users> findAll() {

        try {
            //查找sql
            sql = "select * from users";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行查询
            return (ArrayList<Users>) queryRunner.query(sql, new BeanListHandler<>(Users.class));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users findById(String id) {
        try {
            //查找sql
            sql = "select * from users where id = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行查询
            return queryRunner.query(sql, new BeanHandler<>(Users.class), id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        try {
            //查找sql
            sql = "select * from users where userName = ? and passWord = MD5(?)";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            //执行查询
            return queryRunner.query(sql, new BeanHandler<>(Users.class), userName, passWord);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean userNameExists(String userName) {
        try {
            //查找sql
            sql = "select count(1) from users where userName = ?";

            //获取QueryRunner操作对象
            QueryRunner queryRunner = JdbcUtils.getQueryRunner();

            Long count = queryRunner.query(sql, new ScalarHandler<>(), userName);

            return count >= 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
