package com.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * JDBC API PreparedStatement
 */
public class JDBCDemo7_PreparedStatement {

    @Test
    public void testPreparedStatement() throws Exception {

        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        //String url = "jdbc:mysql:///db1?useSSL = false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入的账号密码
        String name = "zhangsan";
        String pwd = "'or '1'='1";

        // 定义sql（对于preparedStatement）
        String sql = "select * from tb_user where username = ? and password = ?";

        // 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值:sql语句里的问号索引是从1开始的
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);

        // 执行sql
        ResultSet rs = pstmt.executeQuery();


        // 判断是否成功
        if (rs.next())
            System.out.println("success");
        else
            System.out.println("failed");


        // 释放资源
        rs.close();
        pstmt.close();
        conn.close();

    }

    /**
     * PreparedStatement原理
     *
     * @throws Exception
     */
    @Test
    public void testPreparedStatement2() throws Exception {

        //String url = "jdbc:mysql://127.0.0.1:3306/db1";
        //useServerPrepStmts=true 参数开启预编译功能
        String url = "jdbc:mysql:///db1?useSSL = false&useServerPrepStmts=true";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入的账号密码
        String name = "zhangsan";
        String pwd = "'or '1'='1";

        // 定义sql（对于preparedStatement）
        String sql = "select * from tb_user where username = ? and password = ?";

        // 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);


        Thread.sleep(10000);
        /*
        2022-09-17T07:46:01.671197Z	    7 Query	SHOW WARNINGS
        2022-09-17T07:46:01.674212Z	    7 Query	SET character_set_results = NULL
        2022-09-17T07:46:01.674556Z	    7 Query	SET autocommit=1
        2022-09-17T07:46:01.691543Z	    7 Prepare	select * from tb_user where username = ? and password = ?
                        +10s
        2022-09-17T07:46:11.705905Z	    7 Execute	select * from tb_user where username = 'zhangsan' and password = '\'or \'1\'=\'1'
        2022-09-17T07:46:11.715448Z	    7 Execute	select * from tb_user where username = 'aaa' and password = 'bbb'
        2022-09-17T07:46:11.716134Z	    7 Close stmt
        2022-09-17T07:46:11.740618Z	    7 Quit
        证明了在传入sql语句的时候就开始了预编译了
         */
        // 设置？的值:sql语句里的问号索引是从1开始的
        pstmt.setString(1, name);
        pstmt.setString(2, pwd);
        ResultSet rs = null;
        rs = pstmt.executeQuery();

        pstmt.setString(1, "aaa");
        pstmt.setString(2, "bbb");
        rs = pstmt.executeQuery();

        /*
        2022-09-17T07:44:25.892426Z	    6 Query	SHOW WARNINGS
        2022-09-17T07:44:25.894681Z	    6 Query	SET character_set_results = NULL
        2022-09-17T07:44:25.894995Z	    6 Query	SET autocommit=1
        执行一次预编译
        2022-09-17T07:44:25.913017Z	    6 Prepare	select * from tb_user where username = ? and password = ?
        2022-09-17T07:44:25.916796Z	    6 Execute	select * from tb_user where username = 'zhangsan' and password = '\'or \'1\'=\'1'
        2022-09-17T07:44:25.924979Z	    6 Execute	select * from tb_user where username = 'aaa' and password = 'bbb'
        2022-09-17T07:44:25.925445Z	    6 Close stmt
        2022-09-17T07:44:25.930806Z	    6 Quit
         */


        // 判断是否成功
        if (rs.next())
            System.out.println("success");
        else
            System.out.println("failed");


        // 释放资源
        rs.close();
        pstmt.close();
        conn.close();

    }
}

