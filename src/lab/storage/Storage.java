package lab.storage;

import lab.Main;
import lab.entity.Entity;
import lab.entity.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                addEntityFromLine(line);
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEntityFromLine(String line) {
        entities.add(entityInstance.createInstanceFromLine(line));
    }

    public void add() {}

    public void edit() {}

    public void delete() {}

    public void find(String by) {}

    public void print(String sortBy) {}

    protected void readAndApplyFilter(ArrayList<Person> people) {}

    public int getNewId() {
        return entities.size() > 0 ? entities.get(entities.size() - 1).getId() + 1 : 1;
    }

    public Entity findByName(String name) {
        for (Entity e : entities) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
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
        return getSelectedIdFromQuickMenu(entities);
    }

    public int getSelectedIdFromQuickMenu(ArrayList<Entity> ents) {
        printWithIndexes(ents);
        System.out.println("Select the item from the list above: ");
        int selected = Main.scanner.nextInt();
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