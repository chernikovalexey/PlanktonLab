package lab.storage;

import lab.entity.Entity;
import lab.entity.Faculty;

import java.util.Scanner;

/**
 * Created by Alexey
 * At 6:48 PM on 3/1/14
 */

public class FacultyStorage extends Storage {
    public FacultyStorage() {
        super("res/faculties.dat", Faculty.class);
    }

    @Override
    public void add() {
        addEntityFromLine(new Faculty(getNewId(), new Scanner(System.in).nextLine()).toString());
    }

    @Override
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        Entity entity = findByName(scanner.nextLine());
        entity.setName(scanner.nextLine());
    }

    @Override
    public void delete() {
        entities.remove(findByName(new Scanner(System.in).nextLine()));
    }
}