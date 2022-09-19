package com.jdbc;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC API Result
 */
public class JDBCDemo5_ResultSet {

    /**
     * 执行DQL
     * @throws Exception
     */
    @Test
    public void testResultSet() throws Exception {
        // 1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");
        // 2.获取链接
        // 如果是本机的mysql，可简写
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        //String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 定义sql语句
        String sql = "select * from user1";

        // 获取statement
        Statement stmt = conn.createStatement();

        // 执行sql
        ResultSet resultSet = stmt.executeQuery(sql);

        // 循环处理结果
        // next() 返回布尔值
        // 光标向下移动一行 判断是否有数据
        while (resultSet.next()){

            // int类型是从1开始的，在MySQL里！！！！！！！！！
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            double money = resultSet.getDouble(3);

            System.out.println(id);
            System.out.println(name);
            System.out.println(money);

            System.out.println("------------");
        }

        // 释放资源
        resultSet.close();
        stmt.close();
        conn.close();

    }
}

