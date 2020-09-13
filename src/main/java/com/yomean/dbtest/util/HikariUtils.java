package com.yomean.dbtest.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author hym
 */
public class HikariUtils {

    public static DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:file:E:/H2/test");
        config.setUsername("sa");
        config.addDataSourceProperty("minimumIdle",2);
        config.addDataSourceProperty("maximumIdle",10);
        config.addDataSourceProperty("maximumIdle",10);

//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }

}
