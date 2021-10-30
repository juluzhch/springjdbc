package com.example.demo.service;


import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.utils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

//事务管理使用方式1-编程式
public class UserServiceWithCodeTransaction implements UserService {

    private StudentDao studentDao;
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.transactionTemplate=new TransactionTemplate(transactionManager);
        //transactionTemplate 本身也实现TransactionDefinition，对事务的定义可以通过transactionTemplate 设置
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

    }

    @Override
    public void setStudentDao(StudentDao studentDao){
        this.studentDao=studentDao;
    }

    @Override
    public int InsertStudent(Student student){
        //调用流程transactionTemplate.exceute->transactionManager.getTransaction(TransactionDefinition)->回调（dao)
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                return studentDao.Insert(student);
            }
        });
    }

    @Override
    public void InsertStudentWithRollBack(Student student) {
        transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
//                Student maxIdStudent = studentDao.getMaxIdStudent();
//                utils.Print(maxIdStudent);
                return studentDao.InsertThrowException(student);
            }
        });
        Student maxIdStudent = studentDao.getMaxIdStudent();
        utils.Print(maxIdStudent);
    }

    @Override
    public Student getMaxIdStudent() {
        return studentDao.getMaxIdStudent();
    }
}
