package lab.storage;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Lectorn;
import lab.entity.Student;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alexey
 * At 10:29 PM on 3/1/14
 */

public class StudentStorage extends Storage {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Entity> students = new ArrayList<Entity>();

    public StudentStorage() {
        super("res/students.dat", Student.class);
    }

    @Override
    public void add() {
        System.out.print("Input lectorn: ");
        int lectorn = Main.storages.get("lectorn").findByName(scanner.nextLine()).getId();

        System.out.print("Input studing year: ");
        int year = scanner.nextInt();

        System.out.print("Input name: ");
        String name = new Scanner(System.in).nextLine();

        addEntityFromLine(new Student(getNewId(), lectorn, year, name).toString());
    }

    @Override
    public void edit() {
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Input name, second or middle name: ");
        String fullName = Main.toUpperCaseFirstLetter(new Scanner(System.in).nextLine());
        findByFullName(fullName);

        if (students.size() > 1) {
            printSort(students);
        	System.out.println("Input index of students: ");
        }
        Student st = (Student) students.get(students.size() > 1 ? scanner.nextInt()-1 : 0);
        
        printEntity(st);
        System.out.println("___________");
        
        System.out.println("Input lectorn: ");
        String lectorn = new Scanner(System.in).nextLine();      
        if (!lectorn.trim().isEmpty())
        	st.setLectorn(Main.storages.get("lectorn").findByName(lectorn).getId());

        System.out.println("Input studying year: ");
        String year = new Scanner(System.in).nextLine();
        if (!year.trim().isEmpty())
        	st.setYear(Integer.parseInt(year));

        System.out.println("Input full name: ");
        String name = new Scanner(System.in).nextLine();
        if (!name.trim().isEmpty())
        	st.setName(name);
    }

    @Override
    public void delete() {
    	System.out.print("Input name or surname or middle name: ");
    	findByFullName(scanner.nextLine());
    	printSort(students);

    	entities.remove((Student) students.get(scanner.nextInt()-1));
    }

    public Entity findByFullName(String name) {
        //entities
	        for (Entity entity : entities) {
                Student e = (Student) entity;
	            if (e.getName().equals(name) || 
	            	e.getSurname().equals(name) ||
	            	e.getMiddleName().equals(name)) {
	                students.add(e);
	            }
	        }
        return null;
    }



}