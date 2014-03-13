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
        addEntityFromLine(new Faculty(getNewId(), Main.scanner.nextLine()).toString());
    }

    @Override
    public void edit() {
        Entity entity = findByName(Main.scanner.nextLine());
        entity.setName(Main.scanner.nextLine());
    }

    @Override
    public void delete() {
        entities.remove(findByName(Main.scanner.nextLine()));
    }
}