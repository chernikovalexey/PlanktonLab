package lab.entity;

import lab.Main;

public abstract class Person extends Entity {
    protected int department;

    public String getName() {
        return name.split(" ")[0];
    }

    public String getMiddleName() {
        return name.split(" ")[2];
    }

    public String getSurname() {
        return name.split(" ")[1];
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getFaculty() {
        return ((Department) Main.storages.get("department").findById(department)).getFaculty();
    }
}