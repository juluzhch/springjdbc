package com.example.demo.dao.jdbc;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcStudentDao implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int Insert(Student student) {
        String sql="insert into student_t (name,age) values (?,?);";
        return jdbcTemplate.update(sql,student.getName(),student.getAge());
    }

    @Override
    public int InsertThrowException(Student student) {
        int result = this.Insert(student);
        throw new RuntimeException("insert error");

    }

    @Override
    public Student getById(long id) {
        return null;
    }

    @Override
    public Student getMaxIdStudent() {
        final Student student=new Student();
        jdbcTemplate.query("select id,name,age from student_t order by id desc limit 1", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                student.setName(rs.getString("name"));
                student.setAge(rs.getLong("age"));
                student.setId(rs.getLong("id"));
            }
        });
        return student;
    }

    @Override
    public int Update(Student student) {
        return 0;
    }
}
