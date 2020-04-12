package cn.zedongw.bms.dao.impl;

import cn.zedongw.bms.dao.IBooksDao;
import cn.zedongw.bms.entity.Books;
import cn.zedongw.bms.entity.PageBean;
import cn.zedongw.bms.utils.comparator.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
     * 获取Hiberna的Session
     */
    private final Session session = HibernateUtils.getSession();

    @Override
    public void add(Books book) {
        //保存书本
        session.save(book);
    }

    @Override
    public void delete(String id) {
        //删除书本
        session.delete(new Books(id));
    }

    @Override
    public void update(Books book) {
        //开启事务
        Transaction tx = session.beginTransaction();
        try {
            //更新书本
            session.update(book);
        } finally {
            tx.commit();
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
        //开启事务
        Transaction tx = session.beginTransaction();
        try {
            //分页查询书本
            Query query = session.createQuery("from Books");

            // 设置分页参数
            query.setFirstResult(index);
            query.setMaxResults(pageCount);

            pb.setPageData((ArrayList<Books>) query.list());
        } finally {
            tx.commit();
        }
    }


    @Override
    public Books findById(String id) {
        //HQL语句
        String hql = "from Books where id = ?1";
        //开启事务
        Transaction tx = session.beginTransaction();
        try {
            //HQL查询
            Query query = session.createQuery(hql);
            //设置参数
            query.setParameter(1, id);
            //返回查询结果
            return (Books) query.uniqueResult();
        } finally {
            tx.commit();
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
        //HQL语句
        String hql = "select count(b.id) from Books b";
        //开启事务
        Transaction tx = session.beginTransaction();
        try {
            //创建HQL查询
            Query query = session.createQuery(hql);
            //返回查询结果
            return ((Long) query.setCacheable(true).uniqueResult()).intValue();
        } finally {
            tx.commit();
        }
    }
}
