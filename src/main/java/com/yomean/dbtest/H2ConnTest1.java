package com.yomean.dbtest;

import com.yomean.dbtest.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;

/**
 * Test H2 Connect
 * @author hym
 */
public class H2ConnTest1 {

    private Properties properties = PropertiesUtil.getProperties();

    private static final String JDBC_URL = PropertiesUtil.getValue("jdbc.url");

    private static final String USER = PropertiesUtil.getValue("jdbc.user");

    private static final String PASSWORD = PropertiesUtil.getValue("jdbc.password");

    private static final String DRIVER_CLASS = PropertiesUtil.getValue("jdbc.driverClass");;

    public static void main(String[] args) throws Exception {

        Class.forName(DRIVER_CLASS);
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','大日如来','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','青龙','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','白虎','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','朱雀','女')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','玄武','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID()+ "','苍狼','男')");
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name")+ "," + rs.getString("sex"));
        }
        //释放资源
        stmt.close();
        //关闭连接
        conn.close();
    }
}