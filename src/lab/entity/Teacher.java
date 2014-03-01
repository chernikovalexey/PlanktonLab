package lab.entity;

/**
 * Created by Alexey
 * At 10:25 PM on 3/1/14
 */

public class Teacher extends Person {
    private int lectorn;

    public Teacher() {}

    public Teacher(int id, int lectorn, String name) {
        this.id = id;
        this.lectorn = lectorn;
        this.name = name;
    }
}