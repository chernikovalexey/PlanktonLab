package lab.entity;

/**
 * Created by Alexey
 * At 9:50 PM on 3/1/14
 */

public class Person extends Entity {
    public String getName() {
        return name.split(" ")[0];
    }

    public String getMiddleName() {
        return name.split(" ")[2];
    }

    public String getSurname() {
        return name.split(" ")[1];
    }
}