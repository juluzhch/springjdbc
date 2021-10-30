package com.example.demo.dao.support;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataSourceSupport {

    public static String connectionUrl="jdbc:mysql://192.168.174.130:3306/Test?useUnicode=true&characterEncoding=UTF-8&user=root&password=root";

    public static DataSource getDriverManagerDataSource(){
        DataSource dataSource=new DriverManagerDataSource(connectionUrl);
        return dataSource;
    }
}
