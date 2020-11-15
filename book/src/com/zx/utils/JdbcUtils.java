package com.zx.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns= new ThreadLocal<>();
    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * 获取数据库连接池中的连接
    *return
     * */
    public static Connection getConnect(){
        //获取连接，把连接放入ThreadLocal中
        Connection coon = conns.get();
        if (coon == null){
            try {
                coon = dataSource.getConnection();
                conns.set(coon);
                coon.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return coon;
    }

    /**
    * @Description：[用一句话描述方法] 提交事务并关闭
    * @Param
    * @return
    */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if (conn != null){
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
        //线程池里面设置了ThreadLocal变量一定要记得及时清理，
        // 因为线程池里面的核心线程是一直存在的，
        // 如果不清理，那么线程池的核心线程的threadLocals变量一直会持有ThreadLocal变量。
    }

    /**
    * @Description：[用一句话描述方法] 回滚数据并 关闭流
    * @Param
    * @return
    */
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if ( conn != null) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove(); // tomcat底层使用了线程池技术，不remove会报错。。
    }


}

