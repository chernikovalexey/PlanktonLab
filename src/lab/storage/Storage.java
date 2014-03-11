package lab.storage;

import lab.entity.Entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Alexey
 * At 6:57 PM on 3/1/14
 */

public abstract class Storage {
    protected String fileName;
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    protected Entity entityInstance;

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
        Entity entity = entityInstance.createInstanceFromLine(line);
        entities.add(entity);
    }

	public void printEntity(Entity ent) {
		//System.out.println(entityInstance + "_");
        entityInstance.printEntity(ent);
	}

    public void add() {}

    public void edit() {}

    public void delete() {}

    public int getNewId() {
        return entities.size() > 0 ? entities.get(entities.size() - 1).getId() + 1 : 1;
    }

    public Entity findByName(String name) {
       //entities
        for (Entity e : entities) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }
    
    public Entity findById(int id) {
        //entities
         for (Entity e : entities) {
             if (e.getId() == id) {
                 return e;
             }
         }
         return null;
     }

    public void printSort(ArrayList<Entity> entities) {
    	int i=0;
    	
        for (Entity entity : entities) {
            System.out.print(++i +") ");
            printEntity(entity);
        }
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