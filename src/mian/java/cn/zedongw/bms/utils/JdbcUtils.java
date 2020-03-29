package cn.zedongw.bms.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @Author ZeDongW
 * @ClassName JdbcUtils
 * @Description DBCP数据库连接工具
 * @Version 3.0
 * @date ：Created in 2019/7/11 7:02
 * @modified By：
 */

public class JdbcUtils {

    /**
     * DBCP连接池
     */
    private static DataSource dataSource;

    //静态代码块初始化数据库连接池
    static {
        try {
            Properties properties = new Properties();
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("/dbcp.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 返回DBUtil对象
     * @Date: 2019/7/11 7:15
     * @Param: []
     * @return: org.apache.commons.dbutils.QueryRunner
     */
    public static QueryRunner getQueryRunner() {
        return new QueryRunner(dataSource);
    }
}
