package lab.storage;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Faculty;

public class FacultyStorage extends Storage {
    public FacultyStorage() {
        super("res/faculties.dat", Faculty.class);
    }

    @Override
    public void add() {
        addEntityFromLine(new Faculty(getNewId(), Main.readLine("Enter the name of the faculty: ")).toString());
        System.out.println("Added.");
    }

    @Override
    public void edit() {
        int fac = getSelectedIdFromQuickMenu("Select the faculty which is supposed to be edited:");
        Entity entity = findById(fac);

        String name = Main.readLine("The new name for it: ");
        entity.setName(name);

        System.out.println("Edited.");
    }

    @Override
    public void delete() {
        int fac = getSelectedIdFromQuickMenu("Select the faculty which you are intended to delete:");
        Entity entity = findById(fac);

        entities.remove(entity);
        System.out.println("There is no " + entity.getName() + " any more.");
    }
}