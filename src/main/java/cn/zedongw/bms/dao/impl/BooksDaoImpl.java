package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName BooksDaoImpl
 * @Description 书本数据操作接口实现类
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 20:16
 * @modified By：
 */
@Repository
public class BooksDaoImpl implements IBooksDao {

    /**
     * 会话工厂
     */
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Books book) {
        //保存书本
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public void delete(String id) {
        //删除书本
        sessionFactory.getCurrentSession().delete(new Books(id));
    }

    @Override
    public void update(Books book) {
        sessionFactory.getCurrentSession().update(book);
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

        //分页查询书本
        Query query = sessionFactory.getCurrentSession().createQuery("from Books");

        // 设置分页参数
        query.setFirstResult(index);
        query.setMaxResults(pageCount);

        pb.setPageData((ArrayList<Books>) query.list());
    }


    @Override
    public Books findById(String id) {
        //HQL语句
        String hql = "from Books where id = ?1";
        //HQL查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //设置参数
        query.setParameter(1, id);
        //返回查询结果
        return (Books) query.uniqueResult();
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
        //HQL语句
        String hql = "select count(b.id) from Books b";
        //创建HQL查询
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        //返回查询结果
        return ((Long) query.setCacheable(true).uniqueResult()).intValue();
    }
}
