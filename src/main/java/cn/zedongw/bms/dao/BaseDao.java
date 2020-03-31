package cn.zedongw.bms.dao;

import cn.zedongw.bms.utils.JdbcUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.*;
import java.util.ArrayList;

/**
 * @Author ZeDongW
 * @ClassName BaseDao
 * @Description 通过元数据重写所有Dao
 * @Version 1.0
 * @date ：Created in 2019/7/9 21:31
 * @modified By：
 */

public class BaseDao {
    /**
     * 数据库连接对象
     */
    Connection conn;

    /**
     * 结果集对象
     */
    ResultSet rs;

    /**
     * 预编译对象
     */
    PreparedStatement psst;

    /**
     * @Author: ZeDongW
     * @Description: 更新基本操作
     * @Date: 2019/7/9 21:34
     * @Param: [sql, paramsValues]
     * @return: void
     */
    public void update(String sql, Object[] paramsValues) {
        try {
            //获取填充后的预编译对象
            getSql(sql, paramsValues);

            //执行更新
            psst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(rs, psst, conn);
        }
    }

    public <T> ArrayList<T> query(String sql, Object[] paramsValues, Class clazz) {
        //创建Set对象用于保存结果集
        ArrayList<T> list = new ArrayList<>();

        //封装的对象
        T t = null;

        try {
            //获取填充后的预编译对象
            getSql(sql, paramsValues);

            //执行查询
            rs = psst.executeQuery();

            //获取结果集元数据
            ResultSetMetaData metaData = rs.getMetaData();

            //获取结果集列数
            int columnCount = metaData.getColumnCount();

            //遍历封装结果集
            while (rs.next()) {
                t = (T) clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    BeanUtils.copyProperty(t, columnName, columnValue);
                }
                //将对象放入集合中
                list.add(t);
            }

            //返回结果集
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.dbClose(rs, psst, conn);
        }
    }

    /**
     * @Author: ZeDongW
     * @Description: 预编译SQL，并填充参数
     * @Date: 2019/7/9 21:54
     * @Param: [sql, paramsValues]
     * @return: void
     */
    private void getSql(String sql, Object[] paramsValues) throws SQLException {
        //获取数据库连接对象
        conn = JdbcUtils.getConnection();

        //获取预编译对象
        psst = conn.prepareStatement(sql);

        //填充占位符
        //获取预编译对象参数元数据
        ParameterMetaData parameterMetaData = psst.getParameterMetaData();

        //获取参数个数
        int parameterCount = parameterMetaData.getParameterCount();

        //一次填充参数
        for (int i = 1; i <= parameterCount; i++) {
            psst.setObject(i, paramsValues[i - 1]);
        }
    }
}
