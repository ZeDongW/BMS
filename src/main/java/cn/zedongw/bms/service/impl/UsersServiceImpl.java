package cn.zedongw.bms.service.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.dao.impl.UsersDaoImpl;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;

/**
 * @Author ZeDongW
 * @ClassName UsersServiceImpl
 * @Description 用户业务逻辑层接口实现
 * @Version 1.1
 * @date ：Created in 7/3/2019 6:44 AM
 * @modified By：
 */

public class UsersServiceImpl implements IUsersService {

    /**
     * 实例化数据访问层
     */
    IUsersDao dao = new UsersDaoImpl();

    @Override
    public void addUsers(Users user) {
        dao.add(user);
    }

    @Override
    public void deleteUsers(String id) {
        dao.delete(id);
    }

    @Override
    public void updateUsers(Users user) {
        dao.update(user);
    }

    @Override
    public Users findUsersById(String id) {
        return dao.findById(id);
    }

    @Override
    public void findAllUsers(PageBean<Users> pb) {
        dao.findAll(pb);
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        return dao.findByUnAndPwd(userName, passWord);
    }

    @Override
    public Boolean userNameExists(String userName) {
        return dao.userNameExists(userName);
    }
}
