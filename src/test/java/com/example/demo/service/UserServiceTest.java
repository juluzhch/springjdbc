package com.example.demo.service;

import com.example.demo.dao.jdbc.JdbcStudentDao;
import com.example.demo.entity.Student;
import com.example.demo.dao.support.DataSourceSupport;
import com.example.demo.utils;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public class UserServiceTest {

    @Test
    public void testCodeTransactionInsertStudent(){
        UserService userService = getUserServiceOfCodeTransaction();
        userService.InsertStudent(getNewStudent("CodeTransaction"));
        utils.Print(userService.getMaxIdStudent());
    }

    @Test
    public void testCodeTransactionInsertStudentInsertStudentWithRollBack(){
        UserService userService = getUserServiceOfCodeTransaction();
        try {
            userService.InsertStudentWithRollBack(getNewStudent("CodeTransaction"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        utils.Print(userService.getMaxIdStudent());
    }

    private UserService getUserServiceOfCodeTransaction(){
        //service: 事务管理器，dao
        //dataSource事务管理-需要datasource （使用哪种事务管理器，需要与dao层使用的持久化框架一致，比如srping jdbc ，mybatis 等基于datasource 的用DataSourceTransactionManager
        //dao ->jdbcTemplate
        //jdbcTemplate->datasource
        DataSource dataSource=DataSourceSupport.getDriverManagerDataSource();
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        JdbcStudentDao studentDao=new JdbcStudentDao();
        studentDao.setJdbcTemplate(jdbcTemplate);

        //事务管理 使用与Spring jdbc 批量的DataSourceTransactionManager
        PlatformTransactionManager transactionManager=new DataSourceTransactionManager(dataSource);
        //如果使用不同的DataSource ,事务会失效，因为studentDao中jdbcTemplate 使用的连接与transactionManager的连接就不是一个连接，回滚不起作用。
//        PlatformTransactionManager transactionManager=new DataSourceTransactionManager(DataSourceSupport.getDriverManagerDataSource());
        UserServiceWithCodeTransaction userService =new UserServiceWithCodeTransaction();
        userService.setStudentDao(studentDao);
        userService.setTransactionManager(transactionManager);

        return userService;
    }

    private UserService getUserServiceOfXmlTransaction(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("application-xml-transaction.xml");
        return (UserService) ac.getBean("userService");
    }

    private Student getNewStudent(String name){
        Student student=new Student();
        student.setName(name);
        student.setAge(System.currentTimeMillis());
        return student;
    }



}
