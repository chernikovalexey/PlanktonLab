package lab.entity;

/**
 * Created by Alexey
 * At 10:25 PM on 3/1/14
 */

public class Student extends Person {
    private int lectorn;
    private int year;

    public Student() {}

    public Student(int id, int lectorn, int year, String name) {
        this.id = id;
        this.lectorn = lectorn;
        this.year = year;
        this.name = name;
    }
}