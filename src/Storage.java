import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Alexey
 * At 6:57 PM on 3/1/14
 */

public class Storage {
    private String fileName;
    public ArrayList<Entity> entities = new ArrayList<Entity>();
    private Entity entityInstance;

    public Storage(String fileName) {
        this.fileName = fileName;

        try {
            this.entityInstance = Main.classes.get(fileName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileReader fr = new FileReader("res/" + fileName + ".dat");
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

    public void save() {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.write("");

            for (Entity entity : entities) {
                writer.write(entity.toString());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}