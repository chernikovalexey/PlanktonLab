package lab.entity;

import lab.Main;

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
    
    public int getLectorn() {
    	return lectorn;
    }
    
    public void setLectorn(int lectorn) {
    	this.lectorn = lectorn;
    }
    
    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Teacher(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Main.joinArray(parts, " ", 2));
    }
    
    @Override
    public void printEntity(Entity entity) {
    	if (entity == null) return;
    	
    	Teacher tch = (Teacher) entity;
    	Entity lectorn = Main.storages.get("lectorn").findById(tch.getLectorn());
    	
        System.out.println((lectorn == null ? tch.getLectorn() : lectorn.getName()) + " " + tch.getName() + " " + tch.getSurname() + " " + tch.getMiddleName());
    }
    
    @Override
    public String toString() {
        return id + " " + lectorn + " " + getName() + " " + getSurname() + " " + getMiddleName();
    }
}