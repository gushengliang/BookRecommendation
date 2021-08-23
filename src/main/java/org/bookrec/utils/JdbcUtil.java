package org.bookrec.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * JDBC工具类
 *
 * @author a1311
 */
public class JdbcUtil {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(org.bookrec.utils.JdbcUtil.class);
    /**
     * 连接
     */
    private static Connection conn;
    /**
     * sql声明
     */
    private static PreparedStatement ps;
    /**
     * 结果集
     */
    private static ResultSet rs;

    /**
     * 获取连接
     *
     * @throws Exception 异常
     */
    public static void getConnection() throws Exception {
        //连接参数配置文件
        String configFile = "db_mysql.properties";
        try {
            /*
             * 读取配置文件： 从配置文件获取参数 读取配置文件: JdbcUtil.class.getClassLoader()获取资源路径
             * getResourceAsStream()将resource转换成输入
             */
            InputStream is = org.bookrec.utils.JdbcUtil.class.getClassLoader().getResourceAsStream(configFile);
            Properties properties = new Properties();
            properties.load(is);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            // 加载驱动
            Class.forName(driver);
            // 获取连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("配置文件未找到");
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("驱动程序未找到或驱动程序错误");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("连接地址或用户名和密码错误");
        }
    }

    /**
     * 释放资源
     */
    public static void close() throws Exception {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    /**
     * 开启事务
     *
     * @throws Exception 异常
     */
    public static void beginTransaction() throws Exception {
        if (conn != null) {
            throw new SQLException("事务已开启，不能重复开启");
        }
        getConnection();
        conn.setAutoCommit(false);
    }

    /**
     * 提交事务
     *
     * @throws Exception 异常
     */
    public static void commitTransaction() throws Exception {
        if (conn == null) {
            throw new SQLException("没有事务，不能提交！");
        }
        conn.commit();
        conn.close();
    }

    /**
     * 回滚事务
     *
     * @throws Exception 异常
     */
    public static void rollbackTransaction() throws Exception {
        if (conn == null) {
            throw new SQLException("没有事务，不能回滚！");
        }
        conn.rollback();
        conn.close();
        //表示事务结束
        conn = null;
    }


    //-------------------------------封装sql操作------------------------------

    /**
     * 查询返回List集合
     *
     * @param cls class
     * @param sql sql语句
     * @param obj 对象
     * @param <T> 泛型
     * @return 泛型List
     */
    public <T> List<T> getList(Class<T> cls, String sql, Object... obj) throws Exception {

        try {
            //1.获取连接
            //getConnection();
            //2.获取预处理对象
            ps = conn.prepareStatement(sql);

            //循环参数，如果没有就不走这里
            for (int i = 1; i <= obj.length; i++) {
                //注意：数组下标从0开始，预处理参数设置从1开始
                ps.setObject(i, obj[i - 1]);
            }
            //3.执行SQL语句
            System.out.println(sql);
            rs = ps.executeQuery();
            //4.遍历结果集
            //遍历之前准备：因为封装不知道未来会查询多少列，所以我们需要指定有多少列
            //获取ResultSet对象的列编号、类型和属性
            ResultSetMetaData date = rs.getMetaData();
            //获取列数
            int column = date.getColumnCount();
            //获取本类所有的属性
            Field[] fields = cls.getDeclaredFields();
            //创建一个list集合对象来存储查询数据
            List<T> list = new ArrayList<>();
            //开始遍历结果集
            while (rs.next()) {
                //创建类类型实例
                T t = cls.newInstance();
                for (int i = 1; i <= column; i++) {
                    //每一列的值
                    Object value = rs.getObject(i);
                    /*String columnName = date.getColumnName(i);//获取每一列名称
                    关于获取每一列名称，如果列取了别名的话，则不能用上面的方法取列的名称
                    用下面的方法*/
                    //获取每一列名称（别名）
                    String columnName = date.getColumnLabel(i);
                    //遍历所有属性对象
                    for (Field field : fields) {
                        //获取属性名
                        String name = field.getName();
                        //打破封装，忽略对封装修饰符的检测
                        field.setAccessible(true);

                        if (StringUtil.toUnderlineCase(name).equals(columnName)) {
                            BeanUtils.copyProperty(t, name, value);
                            break;//增加效率，避免不必要的循环
                        }
                    }
                }
                list.add(t);
            }
            return list;
            //5.关闭连接
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
    }

    /**
     * 增加、删除、修改
     *
     * @param sql sql语句
     * @param obj 参数
     * @return true or false
     */
    public boolean getDml(String sql, Object... obj) throws Exception {
        try {
            //getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 1; i <= obj.length; i++) {
                ps.setObject(i, obj[i - 1]);
            }
            System.out.println(sql);
            int update = ps.executeUpdate();

            return update > 0;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
    }

    /**
     * 查询返回单个对象
     *
     * @param cls class
     * @param sql sql语句
     * @param obj 对象
     * @param <T> 泛型
     * @return 对象
     * @throws Exception 异常
     */
    public <T> T getOneObject(Class<T> cls, String sql, Object... obj) throws Exception {
        try {
            //1.获取连接
            //getConnection();
            //2.获取预处理对象

            ps = conn.prepareStatement(sql);

            //循环参数，如果没有就不走这里
            for (int i = 1; i <= obj.length; i++) {
                //注意：数组下标从0开始，预处理参数设置从1开始
                ps.setObject(i, obj[i - 1]);
            }
            //3.执行SQL语句
            System.out.println(sql);
            rs = ps.executeQuery();
            //4.遍历结果集
            //遍历之前准备：因为封装不知道未来会查询多少列，所以我们需要指定有多少列
            //获取ResultSet对象的列编号、类型和属性
            ResultSetMetaData date = rs.getMetaData();
            //获取列数
            int column = date.getColumnCount();
            //获取本类所有的属性
            Field[] fields = cls.getDeclaredFields();
            //开始遍历结果集
            if (rs.next()) {
                //创建类类型实例
                T t = cls.newInstance();
                for (int i = 1; i <= column; i++) {
                    //每一列的值
                    Object value = rs.getObject(i);
                    //获取每一列名称
                    String columnName = date.getColumnName(i);
                    //遍历所有属性对象
                    for (Field field : fields) {
                        //获取属性名
                        String name = field.getName();
                        //打破封装，忽略对封装修饰符的检测
                        field.setAccessible(true);
                        if (StringUtil.toUnderlineCase(name).equals(columnName)) {
                            BeanUtils.copyProperty(t, name, value);
                        }
                    }
                }
                return t;
            }
            //5.关闭连接
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param sql sql语句
     * @param obj 对象
     * @return count
     */
    public Integer getCount(String sql, Object... obj) throws Exception {
        try {
            //1.获取连接
            //getConnection();
            //2.获取预处理对象
            ps = conn.prepareStatement(sql);
            //循环参数，如果没有就不走这里
            for (int i = 1; i <= obj.length; i++) {
                //注意：数组下标从0开始，预处理参数设置从1开始
                ps.setObject(i, obj[i - 1]);
            }
            //3.执行SQL语句
            System.out.println(sql);
            rs = ps.executeQuery();
            //开始遍历结果集
            if (rs.next()) {
                return rs.getInt(1);
            }
            //5.关闭连接
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
        return null;
    }

    /**
     * 更新
     *
     * @param sql sql语句
     * @return 影响的行数
     * @throws Exception 异常
     */
    public int update(String sql) throws Exception {
        try {
            // 发送sql
            ps = conn.prepareStatement(sql);
            // 执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
    }

    /**
     * 更新
     *
     * @param sql    sql语句
     * @param params 参数数组
     * @return 影响的行数
     * @throws Exception 异常
     */
    public int update(String sql, Object[] params) throws Exception {
        try {
            // 发送sql
            ps = conn.prepareStatement(sql);
            // 参数赋值
            for (int i = 0; i < params.length; i++) {
                if (params[i] == "NULL") {
                    ps.setObject(i + 1, null);
                } else {
                    ps.setObject(i + 1, params[i]);
                }

            }
            // 执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
    }

    /**
     * 查询
     *
     * @param sql sql语句
     * @return 结果集
     * @throws Exception 异常
     */
    public ResultSet query(String sql) throws Exception {
        try {
            // 发送sql
            ps = conn.prepareStatement(sql);
            // 执行
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
        return rs;
    }

    /**
     * 查询
     *
     * @param sql    sql语句
     * @param params 参数数组
     * @return 结果集
     * @throws Exception 异常
     */
    public ResultSet query(String sql, Object[] params) throws Exception {
        try {
            // 发送sql
            ps = conn.prepareStatement(sql);
            // 参数赋值
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            // 执行
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new Exception("sql语法错误");
        }
        return rs;
    }

    /**
     * 拼接查询条件（暂时不用）
     *
     * @param map map
     * @param obj obj
     * @param <T> T
     * @return String
     */
    @Deprecated
    public static <T> String patchCondition(Map<String, Object> map, Class<T> obj) {
        //拼接字符串
        StringBuilder builder = new StringBuilder();
        Set<String> keySet = map.keySet();
        //迭代map的键
        Iterator<String> iterator = keySet.iterator();
        builder.append(" Where 1=1");
        while (iterator.hasNext()) {
            String key = iterator.next();
            String name;
            //判断是否是别名，如果是就进行截断，获取后面的字段并且首字母转化为小写
            if (key.contains(".")) {
                String[] strArr = key.split("\\.");
                name = strArr[1];
                //首字母转化为小写
                name = name.substring(0, 1).toLowerCase() + name.substring(1);
            } else {
                name = key.substring(0, 1).toLowerCase() + key.substring(1);
            }
            try {
                //获取po中成员变量类型,根据类型判断进行拼接的格式
                Class<?> type = obj.getDeclaredField(name).getType();
                //判断类型是否为string类型
                if (type.isAssignableFrom(String.class)) {
                    //是否为空判断，
                    if (StringUtil.isNotEmpty(map.get(key).toString())) {
                        //找相似的数据；
                        //map.get(key) 是获取和键对应的值；
                        builder.append(" and ").append(key).append(" like '%").append(map.get(key)).append("%'");
                    }
                }
                if (type.isAssignableFrom(int.class)) {
                    if (!"0".equals(map.get(key).toString())) {
                        builder.append(" and ").append(key).append("=").append(map.get(key));
                    }
                }
                if (type.isAssignableFrom(boolean.class)) {
                    builder.append(" and ").append(key).append("=").append(map.get(key));
                }
            } catch (NoSuchFieldException | SecurityException e) {

                e.printStackTrace();
            }
        }
        //返回拼接好的where条件
        return builder.toString();
    }
}
