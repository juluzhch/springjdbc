package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;

public interface UserService {


    public void setStudentDao(StudentDao studentDao);

    public int InsertStudent(Student student);

    public void InsertStudentWithRollBack(Student student);

    Student getMaxIdStudent();



}
