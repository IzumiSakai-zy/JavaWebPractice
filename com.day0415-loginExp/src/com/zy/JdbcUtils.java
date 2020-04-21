package com.zy;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtils {
    private static DataSource ds;
    static {
        Properties pro=new Properties();
        InputStream resourceAsStream = JdbcUtils.class.getClassLoader().
                getResourceAsStream("druid.properties");
        try {
            pro.load(resourceAsStream);
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return ds;
    }
}
