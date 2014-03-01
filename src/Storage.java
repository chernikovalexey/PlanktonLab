import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Alexey
 * At 6:57 PM on 3/1/14
 */

public class Storage {
    private String fileName;
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void load() {
        try {
            FileReader fr = new FileReader("res/" + fileName + ".dat");
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                Main.classes.get(fileName).newInstance().read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {

    }
}