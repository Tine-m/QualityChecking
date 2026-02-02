package app;

import java.time.LocalDate;
import java.time.Period;

public class Employee {
    private String name;
    private LocalDate birthDate;
    private String department;
    private int salary;

    public Employee(String name, LocalDate birthDate, String department, int salary) {
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.salary = salary;
    }

    public int getAge(){
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        return period.getYears();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", Age=" + getAge() +  ", birthDate=" + birthDate + ", department='" + department + '\'' + ", salary=" + salary + '}';
    }
}