package lab.entity;

import lab.Main;

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
    
    public int getYear() {
    	return year;
    }
    
    public void setYear(int year) {
    	this.year = year;
    }
    
    public int getLectorn() {
    	return lectorn;
    }
    
    public void setLectorn(int lectorn) {
    	this.lectorn = lectorn;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Student(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Main.joinArray(parts, " ", 3));
    }

    @Override
    public void printEntity(Entity entity) {
    	if (entity == null) return;
    	
    	Student st = (Student) entity;
    	Entity lectorn = Main.storages.get("lectorn").findById(st.getLectorn());
    	
        System.out.println((lectorn == null ? st.getLectorn() : lectorn.getName()) + " " + st.getYear() + " studying year "+ st.getName() + " " + st.getSurname() + " " + st.getMiddleName());
    }

    @Override
    public String toString() {
        return id + " " + lectorn + " " + year + " " + getName() + " " + getSurname() + " " + getMiddleName();
    }

}