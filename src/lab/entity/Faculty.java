package lab.entity;

import lab.Main;

public class Faculty extends Entity {
    public Faculty() {}

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Faculty(Integer.parseInt(parts[0]), Main.joinArray(parts, " ", 1));
    }
    
    @Override
    public void print() {
    	System.out.println(name);
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}