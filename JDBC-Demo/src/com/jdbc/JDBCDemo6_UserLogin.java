package com.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * UserLogin
 */
public class JDBCDemo6_UserLogin {

    @Test
    public void testLogin() throws Exception {

        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        //String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入的账号密码
        String name = "zhangsan";
        String pwd = "123";

        //SQL语句
        String sql = "select * from tb_user where username = '" + name + "' and password = '" + pwd + "'";

        // 获取stmt对象
        Statement stmt = conn.createStatement();

        // 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        // 判断是否成功
        if (rs.next())
            System.out.println("success");
        else
            System.out.println("failed");


        // 释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

    /**
     * 演示sql注入
     *
     * @throws Exception
     */
    @Test
    public void testLogin_Inject() throws Exception {

        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        //String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入的账号密码
        String name = "zhangsan";
        String pwd = "' or '1'='1";

        //SQL语句
        String sql = "select * from tb_user where username = '" + name + "' and password = '" + pwd + "'";
        // SELECT * FROM tb_user where username = '12312' and password = '' or '1' = '1'
        // 返回给数据库操作时，查询语句变成这个样子，where后面的条件变为真，直接查询所有数据

        // 获取stmt对象
        Statement stmt = conn.createStatement();

        // 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        // 判断是否成功
        if (rs.next())
            System.out.println("success");
        else
            System.out.println("failed");


        // 释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}

