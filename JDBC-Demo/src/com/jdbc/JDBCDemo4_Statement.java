package com.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC API Statement
 */
public class JDBCDemo4_Statement {

    /**
     * 执行DML语句（对数据的增删改）
     *
     * @throws Exception
     */
    @Test
    public void testDML() throws Exception {
        // 1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");
        // 2.获取链接
        // 如果是本机的mysql，可简写
        //String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 定义sql语句
        String sql = "update account set money = 3000 where id = 5";

        // 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();

        // 执行完DML语句受影响的行数
        int cnt = stmt.executeUpdate(sql);
        if (cnt > 0) {
            System.out.println("cg");
        } else {
            System.out.println("sb");
        }

        // 释放资源
        stmt.close();
        conn.close();
    }

    /**
     * 执行DDL语句
     * DDL语句返回值（通过statement类里的executeUpdate方法，一个方法返回受影响行数的值）的有可能是0
     * @throws Exception
     */
    @Test
    public void testDDL() throws Exception {
        // 1.注册驱动
        //Class.forName("com.mysql.jdbc.Driver");
        // 2.获取链接
        // 如果是本机的mysql，可简写
        //String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 定义sql语句
        String sql = "drop database db2";

        // 获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();

        // 执行完DDL语句受影响的行数
        int cnt = stmt.executeUpdate(sql);
        /*if (cnt > 0) {
            System.out.println("cg");
        } else {
            System.out.println("sb");
        }*/
        System.out.println(cnt);

        // 释放资源
        stmt.close();
        conn.close();
    }


}

