package com.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 品牌数据的增删改查操作
 */
public class BrandTest {

    /**
     * 查询所有
     * 1.SQL：select * from tb_brand
     * 2.参数不需要
     * 3.结果：用什么样的数据结构保存：list
     */
    @Test
    public void testSelectAll() throws Exception {
        // 获取connection对象链接(采用druid数据链接池)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //定义sql语句
        String sql = "select * from tb_brand";

        // 获取stmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置参数，因为查询所有数据所有无需设置（？    ？    ？）

        // 执行sql
        ResultSet rs = pstmt.executeQuery();

        // 处理结果
        Brand brand;
        List<Brand> brands = new ArrayList<>();
        while (rs.next()) {
            // 获取数据
            int id = rs.getInt("id");
            String brandName = rs.getString("brand_name");
            String companyName = rs.getString("company_name");
            int ordered = rs.getInt("ordered");
            String description = rs.getString("description");
            int status = rs.getInt("status");

            // 装载Brand对象
            brand = new Brand();
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);

            // 装载集合
            brands.add(brand);
        }

        System.out.println(brands);
        // 释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }

    /**
     * 添加
     * 1.SQL：insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?);
     * 2.参数：除了id之外的所有参数信息
     * 3.结果：Boolean
     */
    @Test
    public void testAdd() throws Exception {

        // 接受页面提交的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球";
        int status = 1;


        // 获取connection对象链接(采用druid数据链接池)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //定义sql语句
        String sql = "insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?);";

        // 获取stmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置参数
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);

        // 执行sql 返回影响行数
        int count = pstmt.executeUpdate();

        // 处理结果
        System.out.println(count > 0);

        // 释放资源
        pstmt.close();
        conn.close();
    }

    /**
     * 修改(根据id修改)
     * 1.SQL：update tb_brand
     * set brand_name  = ?,
     * company_name= ?,
     * ordered     = ?,
     * description = ?,
     * status      = ?
     * where id = ?
     * 2.参数：所有参数信息
     * 3.结果：Boolean
     */
    @Test
    public void testUpdate() throws Exception {

        // 接受前端页面提交的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1000;
        String description = "绕地";
        int status = 1;
        int id = 4;


        // 获取connection对象链接(采用druid数据链接池)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //定义sql语句
        String sql = "update tb_brand\n" +
                "         set brand_name  = ?,\n" +
                "         company_name= ?,\n" +
                "         ordered     = ?,\n" +
                "         description = ?,\n" +
                "         status      = ?\n" +
                "     where id = ?";

        // 获取stmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置参数
        pstmt.setString(1, brandName);
        pstmt.setString(2, companyName);
        pstmt.setInt(3, ordered);
        pstmt.setString(4, description);
        pstmt.setInt(5, status);
        pstmt.setInt(6, id);

        // 执行sql 返回影响行数
        int count = pstmt.executeUpdate();

        // 处理结果
        System.out.println(count > 0);

        // 释放资源
        pstmt.close();
        conn.close();
    }

    /**
     * 删除(根据id修改)
     * 1.SQL：delete from tb_brand where id = ?
     * 2.参数：id
     * 3.结果：Boolean
     */
    @Test
    public void testDelete() throws Exception {

        // 接受前端页面提交的参数
        int id = 4;

        // 获取connection对象链接(采用druid数据链接池)
        Properties prop = new Properties();
        System.out.println(System.getProperty("user.dir"));
        //E:\Projects\Java\JDBC\JDBC-Demo
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        Connection conn = dataSource.getConnection();

        //定义sql语句
        String sql = "delete from tb_brand where id = ?";

        // 获取stmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置参数
        pstmt.setInt(1, id);

        // 执行sql 返回影响行数
        int count = pstmt.executeUpdate();

        // 处理结果
        System.out.println(count > 0);

        // 释放资源
        pstmt.close();
        conn.close();
    }
}
