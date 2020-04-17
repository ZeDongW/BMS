package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IUsersDao;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.entity.Users;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName UsersDaoImpl
 * @Description 用户操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:13
 * @modified By：
 */
@Repository
public class UsersDaoImpl implements IUsersDao {

    /**
     * 会话工厂
     */
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Users user) {
        //新增用户sql
        String sql = "insert into users(id, userName, passWord) values (?1, ?2, MD5(?3))";

        //新增用户
        NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter(1, user.getId());
        query.setParameter(2, user.getUserName());
        query.setParameter(3, user.getPassWord());
        query.executeUpdate();
    }

    @Override
    public void delete(String id) {
        //删除用户
        sessionFactory.getCurrentSession().delete(new Users(id));
    }

    @Override
    public void update(Users user) {
        //修改sql
        String sql = "update users set passWord = MD5(?1) where id = ?2";

        //修改
        NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameter(1, user.getPassWord());
        query.setParameter(2, user.getId());
        query.executeUpdate();
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

        //分页查询书本
        Query query = sessionFactory.getCurrentSession().createQuery("from Users");

        // 设置分页参数
        query.setFirstResult(index);
        query.setMaxResults(pageCount);

        pb.setPageData((ArrayList<Users>) query.list());
    }

    @Override
    public Users findById(String id) {
        //HQL语句
        String hql = "from Users where id = ?1";

        //HQL查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //设置参数
        query.setParameter(1, id);
        //返回查询结果
        return (Users) query.uniqueResult();
    }

    @Override
    public Users findByUnAndPwd(String userName, String passWord) {
        //查找sql
        String sql = "select * from users where userName = ?1 and passWord = MD5(?2)";

        //修改
        NativeQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Users.class);
        query.setParameter(1, userName);
        query.setParameter(2, passWord);
        return (Users) query.uniqueResult();
    }

    @Override
    public Boolean userNameExists(String userName) {
        //HQL语句
        String hql = "select count(u.id) from Users u where u.userName = ?1";

        //创建HQL查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //设置参数
        query.setParameter(1, userName);
        //返回查询结果
        return (Long) query.setCacheable(true).uniqueResult() > 0;
    }

    @Override
    public int getTotalCount() {
        //HQL语句
        String hql = "select count(u.id) from Users u";
        //创建HQL查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //返回查询结果
        return ((Long) query.setCacheable(true).uniqueResult()).intValue();
    }
}
