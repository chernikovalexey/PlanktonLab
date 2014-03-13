package lab.storage;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Teacher;

import java.util.ArrayList;

public class TeacherStorage extends PersonStorage {
    public TeacherStorage() {
        super("res/teachers.dat", Teacher.class);
    }

    @Override
    public void add() {
        System.out.println("Input department: ");
        int department = Main.storages.get("department").findByName(Main.scanner.nextLine()).getId();

        System.out.println("Input name: ");
        String name = Main.scanner.nextLine();

        addEntityFromLine(new Teacher(getNewId(), department, name).toString());
    }

    @Override
    public void edit() {
        System.out.println("Input name, second or middle name: ");

        ArrayList<Entity> teachers = smartFindByFullName(Main.scanner.nextLine());

        if (teachers.size() > 1) { printWithIndexes(teachers); }
        System.out.println("Input index of teacher: ");
        Teacher tch = (Teacher) teachers.get(teachers.size() > 1 ? Main.scanner.nextInt() - 1 : 0);

        tch.print();
        System.out.println("___________");

        System.out.println("Input new department: ");
        String department = Main.scanner.nextLine();
        if (!department.trim().isEmpty()) {
            tch.setDepartment(Main.storages.get("department").findByName(department).getId());
        }

        System.out.println("Input new full name: ");
        String name = Main.scanner.nextLine();
        if (!name.trim().isEmpty()) { tch.setName(name); }
    }

    @Override
    public void delete() {
        System.out.print("Input name or surname or middle Main.scanner: ");
        ArrayList<Entity> teachers = smartFindByFullName(Main.scanner.nextLine());
        printWithIndexes(teachers);
        System.out.println("Input index of teacher ot delete: ");
        entities.remove(teachers.get(Main.scanner.nextInt() - 1));
    }
}