package lab.entity;

import lab.Main;

/**
 * Created by Alexey
 * At 9:05 PM on 3/1/14
 */

public class Lectorn extends Entity {
    private int faculty;

    public Lectorn() {}

    public Lectorn(int id, int faculty, String name) {
        this.id = id;
        this.faculty = faculty;
        this.name = name;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Lectorn(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Main.joinArray(parts, " ", 2));
    }

    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }
    
    public int getFaculty() {
    	return faculty;
    }
    
    @Override
    public void printEntity(Entity entity) {
    	if (entity == null) return;
    	
    	Lectorn lect = (Lectorn) entity;
    	Entity fac = Main.storages.get("faculty").findById(lect.getFaculty());

    	System.out.println("faculty: " + (fac == null ? lect.getFaculty() : fac.getName()) + ", lectorn: " + lect.getName());
    }

    @Override
    public String toString() {
        return id + " " + faculty + " " + name;
    }
}