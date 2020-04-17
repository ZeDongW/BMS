package cn.zedongw.bms.service.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;
import cn.zedongw.bms.service.IUsersService;
import org.springframework.stereotype.Service;

/**
 * @Author ZeDongW
 * @ClassName UsersServiceImpl
 * @Description 用户业务逻辑层接口实现
 * @Version 1.1
 * @date ：Created in 7/3/2019 6:44 AM
 * @modified By：
 */
@Service
public class UsersServiceImpl implements IUsersService {

    /**
     * 实例化数据访问层
     */
    private IUsersDao usersDao;

    public void setUsersDao(IUsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public void addUsers(Users user) {
        usersDao.add(user);
    }

    @Override
    public void deleteUsers(String id) {
        usersDao.delete(id);
    }

    @Override
    public void updateUsers(Users user) {
        usersDao.update(user);
    }

    @Override
    public Users findUsersById(String id) {
        return usersDao.findById(id);
    }

    @Override
    public void findAllUsers(PageBean<Users> pb) {
        usersDao.findAll(pb);
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        return usersDao.findByUnAndPwd(userName, passWord);
    }

    @Override
    public Boolean userNameExists(String userName) {
        return usersDao.userNameExists(userName);
    }
}
