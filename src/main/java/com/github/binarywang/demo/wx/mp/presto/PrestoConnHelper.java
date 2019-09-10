package com.github.binarywang.demo.wx.mp.presto;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * \* @Created with IntelliJ IDEA.
 * \* @author: baibing.shang
 * \* @Date: 2019/1/15
 * \* @Description:
 * \
 */
@Component
public class PrestoConnHelper {
    private static Logger logger = getLogger(PrestoConnHelper.class);

    @Autowired
    private PrestoConnPool pool;

    @Autowired
    private Environment env;

    /**
     * 返回ResultSet
     *
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (StringUtils.isBlank(sql)) {
            logger.error("execute sql cant be empty!");
            return null;
        }
        logger.debug("executr sql is :" + sql);
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(sql);
            if (null != params) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            pool.releaseResource(connection, ps, rs);
            logger.error("PrestoConnHelper SQLException:" + e.getMessage());
            e.printStackTrace();
        } finally {
            pool.closeConnection(connection);
//            releaseResource(connection, ps, rs);
        }
        return null;
    }

    public Integer executeInsertSql(String sql) {
        Connection connection = null;
        Statement stmt = null;
        if (StringUtils.isBlank(sql)) {
            logger.error("execute sql cant be empty!");
            return null;
        }
        logger.debug("executr sql is :" + sql);
        try {
            connection = pool.getConnection();
            stmt = connection.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            pool.releaseResource(connection, stmt);
            logger.error("PrestoConnHelper SQLException:" + e.getMessage());
            e.printStackTrace();
        } finally {
            pool.closeConnection(connection);
//            releaseResource(connection, ps, rs);
        }
        return null;
    }

    public boolean overWrite(String sql) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            boolean flag = statement.execute(sql);
            logger.debug("executr sql is :" + sql);
            return !flag;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("访问presto出错-{}", e);
            pool.releaseResource(connection, statement);
            return false;
        } finally {
            pool.closeConnection(connection);
        }
    }

    public boolean update(String sql) {
        Connection connection = null;
        Statement ps = null;
        if (StringUtils.isBlank(sql)) {
            logger.error("execute sql cant be empty!");
            return false;
        }
        logger.debug("executr sql is :" + sql);
        try {
            connection = pool.getConnection();
            ps = connection.createStatement();
            boolean execute = ps.execute(sql);
            return execute;
        } catch (SQLException e) {
            pool.releaseResource(connection, ps);
            logger.error("PrestoConnHelper SQLException:" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            pool.closeConnection(connection);
//            releaseResource(connection, ps, rs);
        }
    }

    public int queryRows(String sql) {
        Statement stmt = null;
        ResultSet rset = null;
        Connection con = null;
        int rowCount = 0;
        try {
            con = pool.getConnection();
            stmt = con.createStatement();
            rset = stmt.executeQuery(sql);
            while (rset.next()) {
                rowCount = Integer.parseInt(rset.getString("count"));
            }
            return rowCount;
        } catch (SQLException e) {
            pool.releaseResource(con, stmt);
            logger.error("PrestoConnHelper SQLException:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                rset.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pool.closeConnection(con);
        }
        return rowCount;
    }

    /**
     * 执行建表 删表等操作
     *
     * @param sql
     * @return
     */
    public boolean executeSql(String sql) {
        Connection connection = null;
        Statement statement = null;
        if (StringUtils.isBlank(sql)) {
            logger.error("execute sql cant be empty!");
//            return null;
        }
        logger.debug("executr sql is :" + sql);
        connection = pool.getConnection();
        try {
            statement = connection.createStatement();
            boolean flag = statement.execute(sql);
            return !flag;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("访问presto出错-{}", e);
            pool.releaseResource(connection, statement);
            return false;
        } finally {
            pool.closeConnection(connection);
        }

    }

    public List<Map<String, Object>> queryDataByColumn(String sql) {
        System.out.println(sql);
        List<Map<String, Object>> list = new LinkedList<>();
        Connection connection = null;
        try {
            connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet == null) {
                return list;
            }
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("visitorid", resultSet.getObject(1));
                map.put("terminal", resultSet.getObject(2));
                map.put("province", resultSet.getObject(3));
                map.put("channel1", resultSet.getObject(4));
                list.add(map);
            }
            connection.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }finally {
            pool.closeConnection(connection);
        }
    }

    public List<Object[]> queryObjectByColumn(String sql) {
        System.out.println(sql);
        List<Object[]> list = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet == null) {
                return list;
            }
            while (resultSet.next()) {
                Object[] object = new Object[1];
                object[0] = resultSet.getObject(1);
                list.add(object);
            }
            connection.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public List<Map<String, Object>> queryData(String sql) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet == null) {
                return list;
            } else {
                list = ResultSetToList(resultSet);
            }
            connection.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public static List<Map<String, Object>> ResultSetToList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        List<String> colNameList = new ArrayList<String>();
        for (int i = 0; i < colCount; i++) {
            colNameList.add(rsmd.getColumnName(i + 1));
        }
        while (rs.next()) {
            Map map = new HashMap<String, Object>();
            for (int i = 0; i < colCount; i++) {
                String key = colNameList.get(i);
                String value = rs.getString(colNameList.get(i));
                map.put(key, value);
            }
            results.add(map);
        }
        return results;
    }

    /**
     * 如果表存在则删除 否则直接创建表
     *
     * @param tableName
     * @return
     */
    public boolean createTableIfExist(String tableName, String... columns) {

        if (columns.length == 0) {
            throw new RuntimeException("建表失败，传入字段为空");
        }

        String dropTableSql = "drop table if exists " + env.getProperty("presto.realtime.database") + "." + tableName;
        logger.info(new Date() + "-" + dropTableSql);
        boolean b = this.executeSql(dropTableSql);
        if (b) {
            logger.info("删除标签对应表-{}-成功", tableName);
        } else {
            logger.error("删除标签对应表-{}-失败", tableName);
            throw new RuntimeException("删除标签对应表-" + tableName);
        }
        StringBuilder columnSql = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            if (i == 0) {
                columnSql.append(columns[i]).append(" varchar");
            } else {
                columnSql.append(",").append(columns[i]).append(" varchar");
            }
        }
        String createTable = "create table " + env.getProperty("presto.realtime.database") + "." +
                tableName + "( " + columnSql.toString() + ")";
        logger.info(new Date() + "-" + createTable);
        boolean createTableFlag = this.executeSql(createTable);
        if (createTableFlag) {
            logger.info("创建表-{} 成功", tableName);
        } else {
            throw new RuntimeException("创建表-" + tableName + "失败");
        }

        return createTableFlag;
    }

    //resultToList方法
    public static List resultToList(ResultSet rs, Class clazz) {
        //创建一个 T 类型的数组
        List list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            //获取resultSet 的列的信息
            ResultSetMetaData rsm = rs.getMetaData();
            //遍历resultSet
            //遍历每条记录
            while (rs.next()) {
                //实例化对象
                Object obj = clazz.newInstance();
                //取出每一个字段进行赋值
                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    Object value = rs.getObject(i);
                    //匹配实体类中对应的属性
//                    for(int j = 0; j < fields.length; j ++){
                    Field f = fields[i - 1];
                    boolean flag = f.isAccessible();
                    f.setAccessible(true);
                    f.set(obj, value);
                    f.setAccessible(flag);
//                    }
                }
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回list
        return list;
    }

    public List<String> queryTableDesc(String tableName) {

        List<String> list = new LinkedList<>();
        try {
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("desc " + tableName);
            if (resultSet == null) {
                return list;
            }
            while (resultSet.next()) {
                list.add(resultSet.getObject(1).toString());
            }
            connection.close();
            resultSet.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }


}