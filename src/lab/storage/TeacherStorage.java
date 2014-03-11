package lab.storage;

import java.util.ArrayList;
import java.util.Scanner;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Student;
import lab.entity.Teacher;

/**
 * Created by Alexey
 * At 10:28 PM on 3/1/14
 */

public class TeacherStorage extends Storage {
	
	private Scanner scanner = new Scanner(System.in);
    private ArrayList<Entity> teachers = new ArrayList<Entity>();
	
    public TeacherStorage() {
        super("res/teachers.dat", Teacher.class);
    }
    
    @Override
    public void add() {
        System.out.println("Input lectorn: ");
        int lectorn = Main.storages.get("lectorn").findByName(scanner.nextLine()).getId();

        System.out.println("Input name: ");
        String name = scanner.nextLine();

        addEntityFromLine(new Teacher(getNewId(), lectorn, name).toString());
    }

    @Override
    public void edit() {
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Input name, second or middle name: ");

        findByFullName(scanner.nextLine());
        
        if (teachers.size() > 1)
            printSort(teachers);
        	System.out.println("Input index of teacher: ");
        Teacher tch = (Teacher) teachers.get(teachers.size() > 1 ? scanner.nextInt()-1 : 0);
        
        printEntity(tch);
        System.out.println("___________");
        
        System.out.println("Input new lectorn: ");
        String lectorn = scanner.nextLine();
        if (!lectorn.trim().isEmpty())
        	tch.setLectorn(Main.storages.get("lectorn").findByName(lectorn).getId());

        System.out.println("Input new full name: ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty())
        	tch.setName(name);
    }

    @Override
    public void delete() {
    	findByFullName(scanner.nextLine());
    	printSort(teachers);
        System.out.println("Input index of teacher ot delete: ");
    	entities.remove((Teacher) teachers.get(scanner.nextInt()-1));
    }

    public Entity findByFullName(String name) {
        //entities
        for (Entity entity : entities) {
            Teacher e = (Teacher) entity;
            if (e.getName().equals(name) ||
                    e.getSurname().equals(name) ||
                    e.getMiddleName().equals(name)) {
                teachers.add(e);
            }
        }
        return null;
    }
}