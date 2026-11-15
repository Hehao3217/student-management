# 学生管理系统（控制台版）

> 一个 Java 控制台程序，用来练习**面向对象、集合框架和基础异常处理**。
> 学习项目：没有使用任何框架，代码全部手写。

---

## 功能

1. 添加学生信息（学号、姓名、年龄、性别）
2. 修改学生信息（按学号定位）
3. 删除学生信息（按学号定位）
4. 查看全部学生（按学号升序）
5. 退出系统

---

## 技术点

| 技术 | 用在哪 |
|---|---|
| **面向对象** | `Student` 实体类，字段 `private` + `getter/setter` |
| **集合框架** | `List<Student> students = new ArrayList<>()` 存储数据 |
| **工具方法** | `findIndexById` 按学号查找下标，找不到返回 `-1` |
| **输入校验** | 学号不存在时给出提示并返回，不让程序崩溃 |
| **排序** | 冒泡排序，按学号升序 |
| **控制台交互** | `Scanner` + `while(true)` + `switch` 菜单 |

---

## 项目结构

```
src/com/hehao/management/
├── Test01.java       程序入口（main 方法）
├── Student.java      学生实体：学号、姓名、年龄、性别
├── StudentView.java  菜单展示 + 增删改查逻辑
└── ArrayUtils.java   工具类
```

---

## 运行方式

**环境**：JDK 8 及以上

1. 用 IDEA 打开项目
2. 运行 `Test01.java` 的 `main` 方法
3. 按控制台菜单输入 `1` ~ `5` 操作

---

## 设计说明

**1. 为什么用 `List<Student>` 而不是 `Student[]`？**

数组长度固定，删除一个元素要手动 `System.arraycopy` 搬两次，还要额外维护一个 `count` 计数器。`List` 自带 `add / remove / get / set`，长度自己管，少一类"长度和计数不同步"的 bug。

删除的代码量对比：

```java
// 数组版：8 行
Student[] newStudents = new Student[students.length - 1];
System.arraycopy(students, 0, newStudents, 0, index);
System.arraycopy(students, index + 1, newStudents, index, newStudents.length - index - 1);
students = newStudents;
count--;

// List 版：1 行
students.remove(index);
```

**2. 查找为什么单独抽成方法？**

"按学号找下标"这个动作在**修改**、**删除**里都要用。抽出来之后只写一遍，改了也只改一处。

返回 `-1` 表示"没找到"——这是 Java 集合/字符串库的通用约定（`indexOf` 也这么干）。

**3. 为什么调用方必须判断 `-1`？**

因为 `-1` 直接当数组下标或传给 `remove()` 会抛 `IndexOutOfBoundsException`。工具方法只负责"告诉你结果"，**判断结果是否可用是调用方的责任**。

---

## 已知不足（后续改进方向）

| 问题 | 计划怎么改 |
|---|---|
| 数据只在内存里，关掉程序就没了 | 用 IO 把 `List<Student>` 序列化到文件 |
| 界面、业务、数据全挤在 `StudentView` 里 | 拆成 View / Service / DAO 三层 |
| 学号可以重复添加 | 添加前先查重，重复就拒绝 |
| `ArrayUtils` 目前没有被调用 | 要么重新启用，要么删掉，别留着半死不活的类 |
| 用户输入非数字会抛 `InputMismatchException` | 用 `hasNextInt()` 判断后再读 |
| 没有单元测试 | 补 JUnit |

---

## 我学到了什么

1. **数组换 `List`，不只是换写法，是消除一整类 bug。** 删除从 8 行变 1 行，`count` 也不用维护了。
2. **改造时最容易丢的是边界检查。** 注意力都在"正常流程"上，之前加的 `if (index == -1)` 就被顺手删了。所以改完必须专门跑一遍异常路径。
3. **工具方法只报告结果，判断是调用方的事。** 返回 `-1` 而不是抛异常，是更灵活的设计——但要求调用方不能偷懒。

---

*学习项目，用于打基础。*
