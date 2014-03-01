package lab.storage;

import lab.entity.Teacher;

/**
 * Created by Alexey
 * At 10:28 PM on 3/1/14
 */

public class TeacherStorage extends Storage {
    public TeacherStorage() {
        super("res/teachers.dat", Teacher.class);
    }
}