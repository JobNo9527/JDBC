package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC API Connection
 */
public class JDBCDemo3_Connection {

    public static void main(String[] args) throws Exception {

        // 1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        // 2.获取链接
        // 如果是本机的mysql，可简写
        //String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3.定义sql语句
        String sql1 = "update account set money = 3000 where id = 1";
        String sql2 = "update account set money = 3000 where id = 2";

        // 4.获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();

        try {
            // 开启事务
            conn.setAutoCommit(false);
            int count1 = stmt.executeUpdate(sql1);    // 返回受影响行数
            System.out.println(count1);
            //int i = 3/0;
            int count2 = stmt.executeUpdate(sql2);    // 返回受影响行数
            System.out.println(count2);
            // 提交事务
            conn.commit();
        } catch (Exception e) {
            // 回滚事务
            conn.rollback();
            throw new RuntimeException(e);
        }

        // 7.释放资源
        stmt.close();
        conn.close();
    }
}
