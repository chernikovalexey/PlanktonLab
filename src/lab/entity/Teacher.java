package lab.entity;

import lab.Main;

public class Teacher extends Person {
    public Teacher() {}

    public Teacher(int id, int department, String name) {
        this.id = id;
        this.department = department;
        this.name = name;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Teacher(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Main.joinArray(parts, " ", 2));
    }

    @Override
    public void print() {
        Entity dep = Main.storages.get("department").findById(department);
        System.out.println((dep == null ? department : dep.getName()) + " " + getName() + " " + getSurname() + " " + getMiddleName());
    }

    @Override
    public String toString() {
        return id + " " + department + " " + getName() + " " + getSurname() + " " + getMiddleName();
    }
}