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
        System.out.println("Enter the name of the faculty: ");
        addEntityFromLine(new Faculty(getNewId(), Main.scanner.nextLine()).toString());
        System.out.println("Added.");
    }

    @Override
    public void edit() {
        System.out.println("Select the faculty which is supposed to be edited:");
        int fac = getSelectedIdFromQuickMenu();
        Entity entity = findById(fac);
        System.out.println("The new name for it: ");
        entity.setName(Main.scanner.nextLine());
        System.out.println("Edited.");
    }

    @Override
    public void delete() {
        System.out.println("Select the faculty which you are intended to delete:");
        int fac = getSelectedIdFromQuickMenu();
        Entity entity = findById(fac);
        entities.remove(entity);
        System.out.println("There is no " + entity.getName() + " any more.");
    }
}