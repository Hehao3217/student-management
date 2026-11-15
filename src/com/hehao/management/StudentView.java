package com.hehao.management;

//import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    //后面会反复键盘录入
    Scanner sc = new Scanner(System.in);
    List<Student> students = new ArrayList<>();
    //定义一个count记录数组中有多少个对象

//    start方法用于展示页面以及调用功能
public void start(){
        while (true){
            System.out.println("---------学生管理系统---------");
            System.out.println("1 添加学生信息");
            System.out.println("2 修改学生信息");
            System.out.println("3 删除学生信息");
            System.out.println("4 查看学生信息");
            System.out.println("5 退出系统");
            System.out.println("请选择1~5");
        int num = sc.nextInt();
            System.out.println("----------------------------");

            switch (num){
                case 1:
                addStudent();
                break;
                case 2:
                updateStudent();
                break;
                case 3:
                deleteStudent();
                break;
                case 4:
                    findAllStudent();
                    break;
                case 5:
                    System.out.println("是否退出功能，确认请输入0，取消请输入9");
                    int key = sc.nextInt();
                    if(key==0){
                        System.out.println("再见，欢迎再来");
                        return;
                    }
                    break;
            }
        }

    }

    public static int findIndexById(List<Student> students, int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    private void deleteStudent() {


    System.out.println("请您输入要删除的学生学号:");
    int id = sc.nextInt();
    //根据ID查询学生对应的索引位置

        int removeIdndex = findIndexById(students, id);
        if (removeIdndex == -1) {
            System.out.println("学号不存在，请重新选择");
            return;
        }


        students.remove(removeIdndex);
        System.out.println("删除成功");
}

    private void findAllStudent() {
        System.out.println("学号"+"   "+"姓名"+"    "+"年龄"+"    "+"性别");





        if(students.size()==0){
            System.out.println("对不起，现在班级里没有学生信息，待添加.......");
        }else {
            for (int j = students.size()-1; j >0 ; j--) {
                for (int i = 0; i <students.size()-1 ; i++) {
                    if (students.get(i).getId()>students.get(i+1).getId()){
                        Student temp = students.get(i);
                        students.set(i, students.get(i+1));
                        students.set(i+1, temp);
                    }
                }

            }
            for (int i = 0; i <students.size(); i++) {
                System.out.println(students.get(i).getId()+"    "+students.get(i).getName()+"    "+students.get(i).getAge()+"   "+students.get(i).getSex());
            }
        }

    }

    private void updateStudent() {
        System.out.println("请您输入要修改的学生学号:");
        int id = sc.nextInt();
        int updateIddex = findIndexById(students, id);

        if (updateIddex == -1) {
            System.out.println("学号不存在，请重新选择");
            return;
        }

        System.out.println("请您输入学生姓名:");
        String name = sc.next();
        System.out.println("请您输入学生年龄");
        int age = sc.nextInt();
        System.out.println("请您输入学生性别:");
        String sex = sc.next();
//        new Student(id,name,age,sex);
        students.set(updateIddex, new Student(id, name, age, sex));
        System.out.println("修改成功");
    }

    private void addStudent() {
        System.out.println("请您输入学生学号:");
        int id = sc.nextInt();
        System.out.println("请您输入学生姓名:");
        String name = sc.next();
        System.out.println("请您输入学生年龄:");
        int age = sc.nextInt();
        System.out.println("请您输入学生性别:");
        String sex = sc.next();
        //将学生信息封装到Student对象放到students数组中
        students.add(students.size(), new Student(id, name, age, sex));
        System.out.println("添加成功!!!");

    }
}
