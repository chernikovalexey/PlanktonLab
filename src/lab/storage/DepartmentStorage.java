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
        String name = Main.readLine();
        addEntityFromLine(new Department(getNewId(), fac, name).toString());
        System.out.println("Added.");
    }

    @Override
    public void edit() {
        int dep = Main.storages.get("department").getSelectedIdFromQuickMenu("Select the department which is supposed to be edited:");
        Entity entity = findById(dep);

        String d = Main.readLine("Do you want to change the faculty-appurtenance (y/n)?");
        if (d.equals("y")) {
            int fac = Main.storages.get("faculty").getSelectedIdFromQuickMenu("Select the faculty, then:");
            ((Department) entity).setFaculty(fac);
        }

        String d2 = Main.readLine("Do you want to change the name of it (y/n)?");
        if (d2.equals("y")) {
            entity.setName(Main.readLine("Name, please:"));
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
        Main.storages.get("student").save();

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