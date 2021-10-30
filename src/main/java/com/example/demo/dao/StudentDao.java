package com.example.demo.dao;

import com.example.demo.entity.Student;

public interface StudentDao {

    int Insert(Student student);

    int InsertThrowException(Student student);

    Student getById(long id);

    Student getMaxIdStudent();

    int Update(Student student);
}
