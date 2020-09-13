package com.yomean.dbtest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.yomean.dbtest.model.User;
import com.yomean.dbtest.util.HikariUtils;
import com.yomean.dbtest.util.PropertiesUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class dbutils{

    @Test
    public void DruidTest() throws Exception{
        Properties properties = PropertiesUtil.getProperties();
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        for (int i = 0; i < 100000; i++) {
            QueryRunner runner = new QueryRunner(dataSource);
            runner.update("DROP TABLE IF EXISTS USER");
            runner.update("CREATE TABLE USER (id BIGINT IDENTITY PRIMARY KEY, name VARCHAR(30) , age INT )");
            runner.update("INSERT INTO USER VALUES('123','yomean','18')");
            User user = runner.query("SELECT * FROM USER WHERE id = '123'", new BeanHandler<User>(User.class));
            //System.out.println(user);
        }

    }

    @Test
    public void HikariTest() throws SQLException{
        DataSource dataSource = HikariUtils.getDataSource();
        for (int i = 0; i < 100000; i++) {
            QueryRunner runner = new QueryRunner(dataSource);
            runner.update("DROP TABLE IF EXISTS USER");
            runner.update("CREATE TABLE USER (id BIGINT IDENTITY PRIMARY KEY, name VARCHAR(30) , age INT )");
            runner.update("INSERT INTO USER VALUES('123','yomean','18')");
            User user = runner.query("SELECT * FROM USER WHERE id = '123'", new BeanHandler<User>(User.class));
            //System.out.println(user);
        }
    }

    @Test
    public void C3P0Test() throws SQLException{
        DataSource dataSource = new ComboPooledDataSource();
        for (int i = 0; i < 100000; i++) {
            QueryRunner runner = new QueryRunner(dataSource);
            runner.update("DROP TABLE IF EXISTS USER");
            runner.update("CREATE TABLE USER (id BIGINT IDENTITY PRIMARY KEY, name VARCHAR(30) , age INT )");
            runner.update("INSERT INTO USER VALUES('123','yomean','18')");
            User user = runner.query("SELECT * FROM USER WHERE id = '123'", new BeanHandler<User>(User.class));
            //System.out.println(user);
        }
    }

    //--6.MapListHandler：将查询的结果的每一行存入到一个map中，键为列名，值为各列值；然后再将map存入list中；
    @Test
    public void query6() throws SQLException{

        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        QueryRunner runner=new QueryRunner(new ComboPooledDataSource("demo"));
        List<Map<String,Object>> map=runner.query("select * from account",new MapListHandler());
        System.out.println(map);
    }

    //--5.MapHandler：将查询的结果的第一行存入到一个map中，键为列名，值为各列值；
    @Test
    public void query5() throws SQLException{

        QueryRunner runner=new QueryRunner(new ComboPooledDataSource());
        Map<String,Object> map=runner.query("select * from account",new MapHandler());
        System.out.println(map);
    }

    //--4.BeanListHandler：将查询的结果的每一行封装到一个javabean对象中，然后再将这些对象存入list中；
    @Test
    public void query4() throws SQLException{

        QueryRunner runner=new QueryRunner(new ComboPooledDataSource());
        List<User> list=runner.query("select * from account",new BeanListHandler<User>(User.class));
        System.out.println(list);
    }


    //--3.BeanHandler：将查询的结果的第一行封装到一份javabean对象中；
    @Test
    public void query3() throws SQLException{

        QueryRunner runner=new QueryRunner(new ComboPooledDataSource());
        User user=runner.query("select * from USER_INFO",new BeanHandler<User>(User.class));
        System.out.println(user.toString());
    }


    //--2.ArrayListHandler：将查询的结果的每一行放到一个数组中，然后再将数组放到集合中；
    @Test
    public void query2() throws SQLException{

        QueryRunner runner=new QueryRunner(new ComboPooledDataSource());
        List<Object[]> list=runner.query("select * from account",new ArrayListHandler());
        System.out.println(list);
    }

    //--1.ArrayHandler：将查询的结果的第一行放到一个数组中
    @Test
    public void query1() throws SQLException{

        QueryRunner runner=new QueryRunner(new ComboPooledDataSource());
        Object[] array=runner.query("select * from account",new ArrayHandler());
        System.out.println(array);
    }
}