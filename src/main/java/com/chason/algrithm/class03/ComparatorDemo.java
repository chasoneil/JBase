package com.chason.algrithm.class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ComparatorDemo {

    public static class Student {

        public String name;

        public int id;

        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student[" + name + "-" + id + "-" + age + "]";
        }
    }

    public static class AgeAscComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }


    public static class IDAesComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }



    public static void main(String[] args) {

        Student student1 = new Student("A", 2, 18);
        Student student2 = new Student("B", 3, 17);
        Student student3 = new Student("C", 1, 19);

        Student[] students = new Student[] {student1, student2, student3};

        // 使用年龄升序排序
        System.out.println("未排序:");
        print(students);

        System.out.println("排序后:");
        Arrays.sort(students, new AgeAscComparator());
        print(students);

        System.out.println("============================");

        // 使用id 降序排序
        System.out.println("未排序:");
        print(students);

        System.out.println("排序后:");
        Arrays.sort(students, new IDAesComparator());
        print(students);

        System.out.println("===========================");

        // 使用有序表直接排序
        // treeMap 不会加重复的key
        TreeMap<Student, String> studentTree = new TreeMap<>(new IDAesComparator());

        studentTree.put(student1, "A");
        studentTree.put(student2, "B");
        studentTree.put(student3, "C");

        for (Student s : studentTree.keySet()) {
            System.out.println(s.toString());
        }

        System.out.println("============================");
    }


    public static void print (Student[] students) {
        Arrays.stream(students).forEach(s -> System.out.println(s.toString()));
    }
}
