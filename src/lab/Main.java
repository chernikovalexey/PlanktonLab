package lab;

import lab.storage.DepartmentStorage;
import lab.storage.FacultyStorage;
import lab.storage.Storage;
import lab.storage.StudentStorage;
import lab.storage.TeacherStorage;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public interface ReadFileCallback {
        public void readLine(String line);
    }

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

    public static void readFile(String fileName, ReadFileCallback cb) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                cb.readLine(line);
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printHelp() {
        readFile("res/help.txt", new ReadFileCallback() {
            @Override
            public void readLine(String line) {
                System.out.println(line);
            }
        });
    }

    public static String readLine(String placeholder) {
        if (!placeholder.isEmpty()) {
            System.out.println(placeholder);
        }
        String str;
        while (Validator.isEmpty(str = scanner.nextLine())) {}
        return str;
    }

    public static String readLine() {
        return readLine("");
    }

    public static void main(String[] args) {
        for (Storage storage : storages.values()) {
            try {
                storage.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        printHelp();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > 0 && line.charAt(0) == '-') {
                String[] parts = line.substring(1).split(" ");
                String commandName = parts[0];

                if (commandName.equals("exit")) {
                    break;
                } else if (commandName.equals("help")) {
                    printHelp();
                    continue;
                }

                try {
                    storages.get(parts[1]).getClass().getMethod(commandName, String.class).invoke(storages.get(parts[1]), joinArray(parts, " ", 2));
                } catch (Exception me) {
                    // In case of argument-method calling fail
                    try {
                        storages.get(parts[1]).getClass().getMethod(commandName).invoke(storages.get(parts[1]));
                        storages.get(parts[1]).save();
                    } catch (Exception e) {
                        System.out.println("An error occurred. Perhaps, you've made a typo in the method name or specified not all arguments. Try again, anyway!");
                        //e.printStackTrace();
                    }
                }
            }
        }
    }
}