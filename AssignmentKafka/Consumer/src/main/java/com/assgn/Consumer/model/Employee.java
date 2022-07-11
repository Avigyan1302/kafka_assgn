package com.assgn.Consumer.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    public int id;
    public String name;
    public int age;
    public String dept;
    public double salary;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                '}';
    }
}
