package cn.zedongw.bms.utils;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @Author ZeDongW
 * @ClassName MyDataSourcePool
 * @Description 自定义数据库连接池
 * @Version 1.0
 * @date ：Created in 2019/7/10 8:09
 * @modified By：
 */

public class MyDataSourcePool {

    /**
     * 关闭方法
     */
    private static final String CLOSE = "close";
    /**
     * 当前连接数
     */
    public static int curr_count = 0;
    /**
     * 初始连接数
     */
    private static int init_size = 3;
    /**
     * 最大连接数
     */
    private static int max_count = 6;
    /**
     * 数据库驱动类
     */
    private static String driverClass;
    /**
     * 数据库连接地址
     */
    private static String url;
    /**
     * 数据库用户名
     */
    private static String userName;
    /**
     * 数据库密码
     */
    private static String passWord;
    /**
     * 连接池集合,频繁获取释放连接，使用LinkedList集合
     */
    private static LinkedList<Connection> list = new LinkedList<Connection>();

    /**
     * 静态代码块--加载配置文件，初始化数据库连接池
     */
    static {
        try {
            //配置文件对象
            Properties properties = new Properties();

            //加载配置文件
            properties.load(MyDataSourcePool.class.getClassLoader().getResourceAsStream("/db.properties"));

            //获取配置信息
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            userName = properties.getProperty("userName");
            passWord = properties.getProperty("passWord");

            //初始化数据库连接池
            for (int i = 0; i < init_size; i++) {
                //往数据库连接池添加连接
                list.addLast(createConn());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 创建数据库连接对象
     * @Date: 2019/7/10 22:27
     * @Param: []
     * @return: java.sql.Connection
     */
    public static Connection createConn() {
        try {
            //加载数据库驱动
            Class.forName(driverClass);

            //获取数据库连接
            final Connection conn = DriverManager.getConnection(url, userName, passWord);

            //动态代理方法
            Connection proxy = (Connection) Proxy.newProxyInstance(
                    //获取类加载器
                    conn.getClass().getClassLoader(),
                    //目标对象实现的接口
                    new Class[]{Connection.class},
                    //代理事务处理器
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            //代理方法返回的值
                            Object result = null;

                            //获取方法名close
                            String methodName = method.getName();

                            //判断是否需要代理的方法
                            if (CLOSE.equals(methodName)) {
                                if (list.size() < init_size) { //链接池对象小于当前连接数
                                    //将连接放回连接池
                                    list.addLast((Connection) proxy);
                                } else { //连接池已到达初始连接数
                                    //调用原本方法，销毁连接池
                                    result = method.invoke(conn, args);
                                }
                            } else { //调用原方法
                                result = method.invoke(conn, args);
                            }

                            //返回处理结果
                            return result;
                        }
                    }
            );
            //当前连接数增长
            curr_count++;

            //返回连接对象
            return proxy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 获取连接对象
     * @Date: 2019/7/10 22:39
     * @Param: []
     * @return: java.sql.Connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        //数据库连接池还有连接
        if (list.size() > 0) {
            //直接从连接池中获取连接
            conn = list.removeFirst();
            return conn;
        } else {
            //当前连接数已达到数据库最大连接数
            if (curr_count >= max_count) {
                //抛出异常
                throw new RuntimeException("数据库连接池已达到最大连接数，无法获取新的连接，请先释放连接再获取");
            } else {
                //创建连接返回
                conn = createConn();
                return conn;
            }
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 关闭数据库连接
     * @Date: 2019/7/10 22:40
     * @Param: [conn, pstmt, rs]
     * @return: void
     */
    public static void dbClose(Connection conn) {
        try {
            //数据库连接对象不为空
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
