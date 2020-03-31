package cn.zedongw.bms.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author ZeDongW
 * @ClassName DBUtils
 * @Description MySQL JDBC 工具
 * @Version 1.0
 * @date ：Created in 2019/6/2 0002 19:40
 * @modified By：
 */

public class JdbcUtils {

    /**
     * JDBC 驱动类名
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
     * 用户名密码
     */
    private static String passWord;

    /**
     * JDBC 连接
     */
    private static Connection conn;

    static {
        try {
            //读取配置文件并赋值
            Properties properties = new Properties();
            properties.load(JdbcUtils.class.getResourceAsStream("/db.properties"));
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            userName = properties.getProperty("userName");
            passWord = properties.getProperty("passWord");
            //加载驱动类
            Class.forName(driverClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 根据配置文件获取数据库连接对象
     * @Date: 2019/6/2 0002 19:49
     * @Param: []
     * @return: java.sql.Connection
     */
    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, userName, passWord);
        return conn;
    }

    /**
     * @Author: ZeDongW
     * @Description: 数据操作完毕后关闭数据库连接
     * @Date: 2019/6/2 0002 20:00
     * @Param: [rs, pstmt, conn]
     * @return: void
     */
    public static void dbClose(Connection conn) {
        try {
            //数据库连接对象
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
