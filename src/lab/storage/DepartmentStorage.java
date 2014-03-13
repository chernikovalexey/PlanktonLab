package lab.storage;

import lab.Main;
import lab.entity.Department;
import lab.entity.Entity;

import java.util.ArrayList;

public class DepartmentStorage extends Storage {
    public DepartmentStorage() {
        super("res/deps.dat", Department.class);
    }

    @Override
    public void add() {
        System.out.println("Select the faculty:");
        int fac = Main.storages.get("faculty").getSelectedIdFromQuickMenu();
        System.out.println("The name for the new academic department:");
        String name;
        while ((name = Main.scanner.nextLine()).isEmpty()) {}
        addEntityFromLine(new Department(getNewId(), fac, name).toString());
        System.out.println("Added.");
    }

    @Override
    public void edit() {
        System.out.println("Select the department which is supposed to be edited:");
        int dep = Main.storages.get("department").getSelectedIdFromQuickMenu();
        Entity entity = findById(dep);
        System.out.println("Do you want to change the faculty-appurtenance (y/n)?");

        String d;
        while ((d = Main.scanner.nextLine()).isEmpty()) {}
        if (d.equals("y")) {
            System.out.println("Select the faculty, then:");
            int fac = Main.storages.get("faculty").getSelectedIdFromQuickMenu();
            ((Department) entity).setFaculty(fac);
        }

        System.out.println("Do you want to change the name of it (y/n)?");
        String d2;
        while ((d2 = Main.scanner.nextLine()).isEmpty()) {}
        if (d2.equals("y")) {
            System.out.println("Name, please:");
            entity.setName(Main.scanner.nextLine());
        }

        System.out.println("Edited.");
    }

    @Override
    public void delete() {
        System.out.println("Select it:");
        int dep = Main.storages.get("department").getSelectedIdFromQuickMenu();
        Entity entity = findById(dep);
        entities.remove(entity);

        // remove students of this dep
        ((StudentStorage) Main.storages.get("student")).removeByDepartment(dep);
        Main.storages.get("students").save();

        // remove teachers of this dep
        ((StudentStorage) Main.storages.get("teacher")).removeByDepartment(dep);
        Main.storages.get("teacher").save();

        System.out.println("Deleted.");
    }

    public ArrayList<Entity> findByFaculty(int faculty) {
        ArrayList<Entity> results = new ArrayList<Entity>();
        for (Entity e : entities) {
            Department dep = (Department) e;
            if (dep.getFaculty() == faculty) {
                results.add(dep);
            }
        }
        return results;
    }
}