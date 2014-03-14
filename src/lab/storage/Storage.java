package lab.storage;

import lab.Main;
import lab.Validator;
import lab.entity.Entity;
import lab.entity.Person;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public abstract class Storage {
    protected String fileName;
    protected ArrayList<Entity> entities = new ArrayList<Entity>();
    protected Entity entityInstance;

    protected HashMap<String, Comparator<Entity>> comparators = new HashMap<String, Comparator<Entity>>();

    public Storage(String fileName, Class<? extends Entity> clazz) {
        this.fileName = fileName;

        try {
            this.entityInstance = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        Main.readFile(fileName, new Main.ReadFileCallback() {
            @Override
            public void readLine(String line) {
                addEntityFromLine(line);
            }
        });
    }

    public void addEntityFromLine(String line) {
        entities.add(entityInstance.createInstanceFromLine(line));
    }

    public void add() {}

    public void edit() {}

    public void delete() {}

    public void find(String by) {}

    public void print(String sortBy) {
        if (entities.size() == 0) {
            System.out.println("Storage is empty.");
        } else {
            for (Entity e : entities) {
                e.print();
            }
        }
    }

    protected void readAndApplyFilter(ArrayList<Person> people) {}

    public int getNewId() {
        return entities.size() > 0 ? entities.get(entities.size() - 1).getId() + 1 : 1;
    }

    public Entity findById(int id) {
        for (Entity e : entities) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void printWithIndexes(ArrayList<Entity> entities) {
        int i = 0;
        for (Entity entity : entities) {
            System.out.print(++i + ") ");
            entity.print();
        }
    }

    public int getSelectedIdFromQuickMenu() {
        return getSelectedIdFromQuickMenu("");
    }

    public int getSelectedIdFromQuickMenu(ArrayList<Entity> ents) {
        return getSelectedIdFromQuickMenu(ents, "");
    }

    public int getSelectedIdFromQuickMenu(String placeholder) {
        return getSelectedIdFromQuickMenu(entities, placeholder);
    }

    public int getSelectedIdFromQuickMenu(ArrayList<Entity> ents, String placeholder) {
        System.out.println(placeholder);
        printWithIndexes(ents);
        System.out.println("Select the item from the list above: ");
        int selected;
        while (!Validator.isInRange(selected = Main.scanner.nextInt(), 1, ents.size())) {
            System.out.println("Please, submit the index in the range from 1 to " + ents.size());
        }
        return ents.get(selected - 1).getId();
    }

    public void save() {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.write("");

            for (Entity entity : entities) {
                writer.println(entity.toString());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}