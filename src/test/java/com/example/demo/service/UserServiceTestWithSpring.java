package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class UserServiceTestWithSpring {

    final static String Type_Transaction_FactoryBean="ProxyFactoryBean";
    final static String Type_Transaction_Aop="AOP_TX";
    static String defaultTransactionType=Type_Transaction_FactoryBean;

    @BeforeAll
    public static void init(){
        defaultTransactionType=Type_Transaction_Aop;
//        defaultTransactionType=Type_Transaction_FactoryBean;
    }

    @Test
    public void testCodeTransactionInsertStudent(){
        UserService userService = getUserService();
        userService.InsertStudent(getNewStudent("XmlTransaction"));
        utils.Print(userService.getMaxIdStudent());
    }

    @Test
    public void testCodeTransactionInsertStudentInsertStudentWithRollBack(){
        UserService userService = getUserService();
        try {
            userService.InsertStudentWithRollBack(getNewStudent("XmlTransaction"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        utils.Print(userService.getMaxIdStudent());
    }

    private UserService getUserService(){
        if (defaultTransactionType==Type_Transaction_FactoryBean){
            return getUserServiceOfXmlTransaction();
        }else{
            return getUserServiceOfXmlTransaction4AopConfig();
        }
    }

    private UserService getUserServiceOfXmlTransaction(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("application-xml-transaction.xml");
        return (UserService) ac.getBean("userService");
    }
    private UserService getUserServiceOfXmlTransaction4AopConfig(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("application-xml-aop-transaction.xml");
        return (UserService) ac.getBean("userService");
    }

    private Student getNewStudent(String name){
        Student student=new Student();
        student.setName(name);
        student.setAge(System.currentTimeMillis());
        return student;
    }
}
