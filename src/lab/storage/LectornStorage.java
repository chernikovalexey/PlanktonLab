package lab.storage;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Lectorn;

import java.util.Scanner;

/**
 * Created by Alexey
 * At 9:03 PM on 3/1/14
 */

public class LectornStorage extends Storage {
    public LectornStorage() {
        super("res/lectorns.dat", Lectorn.class);
    }

    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        addEntityFromLine(new Lectorn(getNewId(), Main.storages.get("faculty").findByName(scanner.nextLine()).getId(), scanner.nextLine()).toString());
    }

    @Override
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Entity entity = findByName(name);
        ((Lectorn) entity).setFaculty(Main.storages.get("faculty").findByName(scanner.nextLine()).getId());
        entity.setName(scanner.nextLine());
    }

    @Override
    public void delete() {
        entities.remove(findByName(new Scanner(System.in).nextLine()));
    }
}