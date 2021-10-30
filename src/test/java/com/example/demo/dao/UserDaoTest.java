package com.example.demo.dao;

import com.example.demo.dao.jdbc.JdbcStudentDao;
import com.example.demo.entity.Student;
import com.example.demo.dao.support.DataSourceSupport;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoTest {

    @Test
    public void testSpringJdbc(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(DataSourceSupport.getDriverManagerDataSource());
        JdbcStudentDao studentDao=new JdbcStudentDao();
        studentDao.setJdbcTemplate(jdbcTemplate);
        studentDao.Insert(getNewStudent("jdbc"));
    }
    private Student getNewStudent(String name){
        Student student=new Student();
        student.setName(name);
        student.setAge(System.currentTimeMillis());
        return student;
    }


    void print(Object obj) {
        System.out.println(obj);
    }
}
