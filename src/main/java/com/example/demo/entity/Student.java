package com.example.demo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Student {
    public Student() {
    }

    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    private long id;
    private String name;
    private long age;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {

        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", age2Time=" + getAgeToTime() +
                '}';
    }
    private String getAgeToTime(){
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.CHINA);
        Date date =new Date(this.age);
        return sdr.format(date);
    }
}
