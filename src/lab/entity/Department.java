package lab.entity;

import lab.Main;

public class Department extends Entity {
    private int faculty;

    public Department() {}

    public Department(int id, int faculty, String name) {
        this.id = id;
        this.faculty = faculty;
        this.name = name;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Department(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Main.joinArray(parts, " ", 2));
    }

    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }

    public int getFaculty() {
        return faculty;
    }

    @Override
    public void print() {
        Entity fac = Main.storages.get("faculty").findById(getFaculty());
        System.out.println("faculty: " + (fac == null ? getFaculty() : fac.getName()) + ", department: " + name);
    }

    @Override
    public String toString() {
        return id + " " + faculty + " " + name;
    }
}