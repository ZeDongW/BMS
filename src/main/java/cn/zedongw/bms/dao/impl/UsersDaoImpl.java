package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.MyDataSourcePool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
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
     * 数据库连接对象
     */
    private Connection conn;

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

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行更新
            queryRunner.update(conn, sql, user.getId(), user.getUserName(), user.getPassWord());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

    @Override
    public void delete(String id) {
        try {
            //删除sql
            sql = "delete from users where id = ?";

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行更新
            queryRunner.update(conn, sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

    @Override
    public void update(Users user) {
        try {
            //修改sql
            sql = "update users set passWord = MD5(?) where id = ?";

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行更新
            queryRunner.update(conn, sql, user.getPassWord(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

    @Override
    public ArrayList<Users> findAll() {

        try {
            //查找sql
            sql = "select * from users";

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行查询
            return (ArrayList<Users>) queryRunner.query(conn, sql, new BeanListHandler<>(Users.class));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

    @Override
    public Users findById(String id) {
        try {
            //查找sql
            sql = "select * from users where id = ?";

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行查询
            return queryRunner.query(conn, sql, new BeanHandler<>(Users.class), id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        try {
            //查找sql
            sql = "select * from users where userName = ? and passWord = MD5(?)";

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            //执行查询
            return queryRunner.query(conn, sql, new BeanHandler<>(Users.class), userName, passWord);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

    @Override
    public Boolean userNameExists(String userName) {
        try {
            //查找sql
            sql = "select count(1) from users where userName = ?";

            //获取数据库连接对象
            conn = MyDataSourcePool.getConnection();

            //获取DUUtils操作对象
            QueryRunner queryRunner = new QueryRunner();

            Long count = queryRunner.query(conn, sql, new ScalarHandler<>(), userName);

            return count >= 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDataSourcePool.dbClose(conn);
        }
    }

}
