package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class Springjdbc2Application implements CommandLineRunner {
  @Autowired
  private DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(Springjdbc2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
    }

    private void showConnection() throws SQLException {
        print("database:"+ dataSource.toString());
        Connection conn =dataSource.getConnection();
        print("conn:"+conn.toString());
    }
    private void print(Object obj){
        System.out.println(obj);
    }
}
