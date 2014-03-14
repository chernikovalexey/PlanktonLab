package lab.storage;

import lab.Main;
import lab.entity.Person;
import lab.entity.Teacher;

public class TeacherStorage extends PersonStorage {
    public TeacherStorage() {
        super("res/teachers.dat", Teacher.class);
    }

    @Override
    public void add() {
        int department = Main.storages.get("department").getSelectedIdFromQuickMenu("Enter an academic department:");
        String name = readName();

        addEntityFromLine(new Teacher(getNewId(), department, name).toString());

        System.out.println("Added " + name + " to the database successfully.");
    }

    @Override
    public void edit() {
        Person tch = readAndFindPerson();

        System.out.println("Do you want to change the department (y/n)?");
        String department = Main.readLine();
        if (department.equals("y")) {
            System.out.println("Select the department, then: ");
            tch.setDepartment(Main.storages.get("department").getSelectedIdFromQuickMenu());
        }

        System.out.println("Do you want to change the name (y/n)?");
        String name = Main.readLine();
        if (name.equals("y")) {
            tch.setName(readName());
        }

        System.out.println("Changes made and led to the following: ");
        tch.print();
    }
}