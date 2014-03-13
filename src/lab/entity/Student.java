package lab.entity;

import lab.Main;

public class Student extends Person {
    private int year;

    public Student() {}

    public Student(int id, int department, int year, String name) {
        this.id = id;
        this.department = department;
        this.year = year;
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Student(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Main.joinArray(parts, " ", 3));
    }

    @Override
    public void print() {
        Entity dep = Main.storages.get("department").findById(department);
        System.out.println((dep == null ? department : dep.getName()) + " " + year + " studying year " + getName() + " " + getSurname() + " " + getMiddleName());
    }

    @Override
    public String toString() {
        return id + " " + department + " " + year + " " + getName() + " " + getSurname() + " " + getMiddleName();
    }
}