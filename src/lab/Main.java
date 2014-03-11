package lab;

import lab.entity.Entity;
import lab.entity.Faculty;
import lab.entity.Lectorn;
import lab.entity.Student;
import lab.entity.Teacher;
import lab.storage.FacultyStorage;
import lab.storage.LectornStorage;
import lab.storage.Storage;
import lab.storage.StudentStorage;
import lab.storage.TeacherStorage;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alexey
 * At 6:29 PM on 3/1/14
 */

public class Main {
    public static HashMap<String, Storage> storages = new HashMap<String, Storage>() {{
        put("faculty", new FacultyStorage());
        put("lectorn", new LectornStorage());
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
    
    public static String toUpperCaseFirstLetter(String str){   	
	    final StringBuilder result = new StringBuilder(str.length());
	    String[] words = str.split(" ");
	    
	    for(int i=0,l=words.length;i<l;++i) {
	      if(i>0) result.append(" ");      
	      result.append(Character.toUpperCase(words[i].charAt(0)))
	            .append(words[i].substring(1).toLowerCase());
	
	    }	    
	    return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (Storage storage : storages.values()) {
            try {
                storage.load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == '-') {
                String[] parts = line.substring(1).split(" ");
                String commandName = parts[0];

                if (commandName.equals("exit")) {
                    break;
                }

                try {

                    storages.get(parts[1]).getClass().getMethod(commandName).invoke(storages.get(parts[1]));
                    storages.get(parts[1]).save();
                    int i = 0;
                    for (Entity e : storages.get(parts[1]).entities) {
                    	System.out.print(++i + ") ");
                    	e.printEntity(e);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
        
    }
}