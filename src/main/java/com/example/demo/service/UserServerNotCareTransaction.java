package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;

//服务不关注事务控制，通过aop注入实现
//可以是xml配置的事务注入，也可以是，切面式注入的事务控制
public class UserServerNotCareTransaction  implements UserService{

    private StudentDao studentDao;
    @Override
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao=studentDao;
    }

    @Override
    public int InsertStudent(Student student) {
        return studentDao.Insert(student);
    }

    @Override
    public void InsertStudentWithRollBack(Student student) {
        studentDao.InsertThrowException(student);
    }

    @Override
    public Student getMaxIdStudent() {
        return studentDao.getMaxIdStudent();
    }
}
