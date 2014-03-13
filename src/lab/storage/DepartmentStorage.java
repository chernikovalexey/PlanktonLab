package lab.storage;

import lab.Main;
import lab.entity.Department;
import lab.entity.Entity;

import java.util.ArrayList;
import java.util.Scanner;

public class DepartmentStorage extends Storage {
    public DepartmentStorage() {
        super("res/deps.dat", Department.class);
    }

    @Override
    public void add() {
        addEntityFromLine(new Department(getNewId(), Main.storages.get("faculty").findByName(Main.scanner.nextLine()).getId(), Main.scanner.nextLine()).toString());
    }

    @Override
    public void edit() {
        Entity entity = findByName(Main.scanner.nextLine());
        ((Department) entity).setFaculty(Main.storages.get("faculty").findByName(Main.scanner.nextLine()).getId());
        entity.setName(Main.scanner.nextLine());
    }

    @Override
    public void delete() {
        entities.remove(findByName(new Scanner(System.in).nextLine()));
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