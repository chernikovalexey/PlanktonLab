/**
 * Created by Alexey
 * At 7:07 PM on 3/1/14
 */

public class Faculty extends Entity {
    private int id;
    private String name;

    public Faculty() {}

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Entity createInstanceFromLine(String line) {
        String[] parts = line.split(" ");
        return new Faculty(Integer.parseInt(parts[0]), Main.joinArray(parts, " ", 1));
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}