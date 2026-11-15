package com.hehao.management;

//import javax.swing.*;

public class Student {
   private String name;
   private int id;
   private int age;
   private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Student(int id, String name, int age, String sex) {
        this.id = id;
        this.name = name;   // 注意：参数顺序是 (id, name, age, sex)
        this.age = age;
        this.sex = sex;
    }
}
