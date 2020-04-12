package cn.zedongw.bms.utils.comparator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @ClassName HibernateUtils
 * @Description: Hibernate连接工具
 * @Author ZeDongW
 * @Date 2020/4/12 0012 22:13
 * @Version 1.0
 * @Modified By:
 * @Modified Time:
 **/
public class HibernateUtils {

    /**
     * 初始化SessionFactory
     */
    private static final SessionFactory SF;

    static {
        SF = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Description: 创建（获取）Session
     *
     * @param
     * @throws
     * @methodName: getSession
     * @return: org.hibernate.Session
     * @author: ZeDongW
     * @date: 2020/4/12 0012 22:15
     */
    public static Session getSession() {
        // 线程的方式创建session，必须要配置
        // 可以不用关闭，会自动关。
        return SF.openSession();
    }
}
