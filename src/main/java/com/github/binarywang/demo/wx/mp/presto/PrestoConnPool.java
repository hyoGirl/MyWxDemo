package com.github.binarywang.demo.wx.mp.presto;

import com.github.binarywang.demo.wx.mp.config.HikariPoolConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * \* @Created with IntelliJ IDEA.
 * \* @author: baibing.shang
 * \* @Date: 2019/1/14
 * \* @Description:
 * \
 */
@Component
public class PrestoConnPool {
    private static Logger logger = getLogger(PrestoConnPool.class);
    private static HikariDataSource hikariDataSource;

    @Autowired
    HikariPoolConfig hikariConfig;

    @Autowired
    PrestoConfig prestoConfig;

    /**
     * 初始化连接池
     */
    @PostConstruct
    public void initialize(){
        String URL = prestoConfig.getUrl();
        try {
            try {
                Class.forName("com.facebook.presto.jdbc.PrestoDriver").newInstance();
            } catch (InstantiationException e) {
                logger.error("PrestoConnectionPool InstantiationException:" + e.getMessage());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                logger.error("PrestoConnectionPool IllegalAccessException:" + e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                logger.error("PrestoConnectionPool ClassNotFoundException:" + e.getMessage());
                e.printStackTrace();
            }
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName("com.facebook.presto.jdbc.PrestoDriver");
            hikariDataSource.setJdbcUrl(URL);
            hikariDataSource.setUsername("root");
            hikariDataSource.setPassword("");
            hikariDataSource.setPoolName("DatebookHikariCP");
            //hikariDataSource.setConnectionTestQuery(hikariConfig.getConnectionTestQuery());
            hikariDataSource.setMaximumPoolSize(100);
            hikariDataSource.setConnectionTimeout(30000);
            hikariDataSource.setMinimumIdle(10);
            hikariDataSource.setAutoCommit(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取Connection连接
     * @return
     */

    public Connection getConnection(){
        Connection connection = null;
        try{
            connection =hikariDataSource.getConnection();
        }catch (SQLException e){
            logger.error("PrestoConnectionPool SQLException:" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭Connection连接
     * @param connection
     */
    public void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (SQLException e){
            logger.error("PrestoConnectionPool closeConnection SQLException:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param ps
     * @param rs
     */
    public void releaseResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                    logger.error("出错了e={},关闭rs失败",e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                    logger.error("出错了e={},关闭statement失败",e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                    logger.error("出错了e={},关闭conn失败",e);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param statement
     */
    public void releaseResource(Connection conn, Statement statement) {
        try {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                    logger.error("出错了e={},关闭rs失败",e);
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                    logger.error("出错了e={},关闭conn失败",e);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}