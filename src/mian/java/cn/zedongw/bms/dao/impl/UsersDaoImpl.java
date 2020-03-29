package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.BaseDao;
import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.Users;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author ZeDongW
 * @ClassName UsersDaoImpl
 * @Description 用户操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:13
 * @modified By：
 */

public class UsersDaoImpl extends BaseDao implements IUsersDao {

    @Override
    public void add(Users user) {
        //SQL
        String sql = "insert into users(id, userName, passWord) values (?, ?, MD5(?))";

        //调用父类更新方法
        super.update(sql, new Object[]{user.getId(), user.getUserName(), user.getPassWord()});
    }

    @Override
    public void delete(String id) {
        //SQL
        String sql = "delete from users where id = ?";

        //调用父类更新方法
        super.update(sql, new Object[]{id});
    }

    @Override
    public void update(Users user) {
        //SQL
        String sql = "update users set passWord = MD5(?) where id = ?";

        //调用父类的更新方法
        super.update(sql, new Object[]{user.getPassWord(), user.getId()});
    }

    @Override
    public ArrayList<Users> findAll() {
        //SQL
        String sql = "select * from users";

        //调用父类的查询方法
        ArrayList<Users> usersList = super.query(sql, null, Users.class);

        return usersList;
    }

    @Override
    public Users findById(String id) {
        //SQL
        String sql = "select * from users where id = ?";

        //调用父类的查询方法
        ArrayList<Users> usersList = super.query(sql, new Object[]{id}, Users.class);

        //获取迭代器
        Iterator<Users> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        //SQL
        String sql = "select * from users where userName = ? and passWord = MD5(?)";

        //调用父类查询方法
        ArrayList<Users> usersList = super.query(sql, new Object[]{userName, passWord}, Users.class);

        //获取迭代器
        Iterator<Users> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

    @Override
    public Boolean userNameExists(String userName) {
        //SQL
        String sql = "select * from users where userName = ?";

        //调用父类查询方法
        ArrayList<Users> usersList = super.query(sql, new Object[]{userName}, Users.class);

        return usersList != null && usersList.size() > 0;
    }

}
