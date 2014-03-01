package lab.storage;

import lab.entity.Student;

/**
 * Created by Alexey
 * At 10:29 PM on 3/1/14
 */

public class StudentStorage extends Storage {
    public StudentStorage() {
        super("res/students.dat", Student.class);
    }
}