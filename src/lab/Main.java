package lab;

import lab.storage.DepartmentStorage;
import lab.storage.FacultyStorage;
import lab.storage.Storage;
import lab.storage.StudentStorage;
import lab.storage.TeacherStorage;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public final static Scanner scanner = new Scanner(System.in);

    public final static HashMap<String, Storage> storages = new HashMap<String, Storage>() {{
        put("faculty", new FacultyStorage());
        put("department", new DepartmentStorage());
        put("student", new StudentStorage());
        put("teacher", new TeacherStorage());
    }};

    public static String joinArray(String[] arr, String with, int fromIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = fromIndex, len = arr.length; i < len; ++i) {
            builder.append(arr[i]);
            if (i != len - 1) {
                builder.append(with);
            }
        }
        return builder.toString();
    }

    public static String toUpperCaseFirstLetter(String str) {
        final StringBuilder result = new StringBuilder(str.length());
        String[] words = str.split(" ");

        for (int i = 0, l = words.length; i < l; ++i) {
            if (i > 0) { result.append(" "); }
            result.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1).toLowerCase());

        }
        return result.toString();
    }

    public static void main(String[] args) {
        for (Storage storage : storages.values()) {
            try {
                storage.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > 0 && line.charAt(0) == '-') {
                String[] parts = line.substring(1).split(" ");
                String commandName = parts[0];

                if (commandName.equals("exit")) {
                    break;
                }

                try {
                    storages.get(parts[1]).getClass().getMethod(commandName, String.class).invoke(storages.get(parts[1]), joinArray(parts, " ", 2));
                } catch (Exception me) {
                    // In case of argument-method calling fail
                    try {
                        storages.get(parts[1]).getClass().getMethod(commandName).invoke(storages.get(parts[1]));
                        storages.get(parts[1]).save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}