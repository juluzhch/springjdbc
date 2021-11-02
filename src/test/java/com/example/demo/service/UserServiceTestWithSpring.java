package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class UserServiceTestWithSpring {

    @Test
    public void testCodeTransactionInsertStudent(){
        UserService userService = getUserServiceOfXmlTransaction();
        userService.InsertStudent(getNewStudent("CodeTransaction"));
        utils.Print(userService.getMaxIdStudent());
    }

    @Test
    public void testCodeTransactionInsertStudentInsertStudentWithRollBack(){
        UserService userService = getUserServiceOfXmlTransaction();
        try {
            userService.InsertStudentWithRollBack(getNewStudent("CodeTransaction"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        utils.Print(userService.getMaxIdStudent());
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
