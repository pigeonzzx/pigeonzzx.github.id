package com.zx.dao.impl;

import com.zx.utils.JdbcUtils;
import jdk.jshell.spi.ExecutionControl;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import javax.management.QueryEval;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;

public abstract class BaseDao {
    //使用DButils操作数据库

    private QueryRunner queryRunner = new QueryRunner();
    /**
    * @Description：[用一句话描述方法]
    * @Param
    * @return 返回值1表示成功，返回值-1 失败
    */
    public int update(String sql,Object...args){
        Connection conn = JdbcUtils.getConnect();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
/**
* @Description：[返回多个结果集]
* @Param
* @return null肯定失败
*/
    public <T> List<T> queryForList(Class<T> type,String sql,Object...args){
        Connection conn = JdbcUtils.getConnect();
        try {
            return  queryRunner.query(conn,sql,new BeanListHandler<>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
/**
* @Description：[返回一个结果]
* @Param
* @return
*/
    public <T>T queryForSimple(Class<T> type,String sql,Object... args){
        Connection conn = JdbcUtils.getConnect();
        try {
            return  queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

/**
* @Description：[查询返回一行一列的情况]
* @Param
* @return
*/
    public Object queryForSingleValue(String sql,Object...args){
        Connection conn = JdbcUtils.getConnect();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }






}
