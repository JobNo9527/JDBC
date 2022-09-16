package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC 快速入门
 */
public class JDBCDemo {

    public static void main(String[] args) throws Exception{

        // 1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");

        // 2.获取链接
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username ="root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3.定义sql语句
        String sql = "update account set money = 2000 where id = 1";

        // 4.获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();

        // 5.执行sql
        int count = stmt.executeUpdate(sql);    // 返回受影响行数

        // 6. 处理结果
        System.out.println(count);

        // 7.释放资源
        stmt.close();
        conn.close();
    }
}
