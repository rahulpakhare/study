package org.sapient.tbs.example;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamExample {
    public static void main(String args[]) {
        Employee employee1 = new Employee();
        employee1.salary = 120000;
        employee1.name = "test1";
        employee1.dept = new Department();
        employee1.dept.location = "pune1";
        employee1.dept.name = "dept1";

        Employee employee2 = new Employee();
        employee2.salary = 150000;
        employee2.name = "test2";
        employee2.dept = new Department();
        employee2.dept.location = "pune1";
        employee2.dept.name = "dept2";

        Employee employee3 = new Employee();
        employee3.salary = 210000;
        employee3.name = "test3";
        employee3.dept = new Department();
        employee3.dept.location = "pune3";
        employee3.dept.name = "dept3";

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

//        employeeList.stream().collect(Collectors.toMap(e -> e.getDept().location, e -> e.salary))
//                .compute(Collectors.groupingBy());
        System.out.println(employeeList.stream().map(e -> e.dept).collect(toList()));
        System.out.println(employeeList.stream().collect(groupingBy(e -> e.dept.location, mapping(e -> e.salary, toList()))));
        System.out.println(employeeList.stream().collect(groupingBy(e -> e.dept.location)));

        // System.out.println(employeeList.stream().collect(groupingBy(e -> e.dept.location, maxBy(e -> e.salary))));

    }
}

class Employee {
    public Integer salary;
    public String name;
    public Department dept;

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}

class Department {
    public String location;
    public String name;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}