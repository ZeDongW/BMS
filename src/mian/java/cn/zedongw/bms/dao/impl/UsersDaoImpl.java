package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.utils.DbUtils;
import cn.zedongw.bms.utils.beanutil.UsersUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private Connection conn;
    private String sql;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public void add(Users user) {
        try {
            conn = DbUtils.getConnection();
            sql = "insert into users (id, userName, passWord) values (?, ?, MD5(?))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getPassWord());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public void delete(String id) {
        try {
            conn = DbUtils.getConnection();
            sql = "delete from users where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public void update(Users user) {
        try {
            conn = DbUtils.getConnection();
            sql = "update users set passWord = MD5(?) where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPassWord());
            pstmt.setString(2, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public ArrayList<Users> findAll() {
        ArrayList<Users> list = new ArrayList<Users>();
        try {
            conn = DbUtils.getConnection();
            sql = "select * from users";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                list.add(UsersUtil.getBean(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public Users findById(String id) {
        try {
            conn = DbUtils.getConnection();
            sql = "select * from users where id =?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return UsersUtil.getBean(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DbUtils.dbClose(rs, pstmt, conn);
        }
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord){

        try {

            conn = DbUtils.getConnection();
            //查找sql
            sql = "select * from users where userName = ? and passWord = MD5(?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);
            pstmt.setString(2, passWord);
            rs = pstmt.executeQuery();
            if (rs.next()){
                return UsersUtil.getBean(rs);
            } else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean userNameExists(String userName){
        try {
            conn = DbUtils.getConnection();
            //查找sql
            sql = "select count(1) from users where userName = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (rs.next()){
                int count = rs.getInt(1);
                if (count >= 1){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
