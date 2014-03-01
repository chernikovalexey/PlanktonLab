import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alexey
 * At 6:29 PM on 3/1/14
 */

public class Main {
    private static ArrayList<String> commands = new ArrayList<String>() {{
        add("addfac");
        add("editfac");
        add("delfac");
    }};

    public static HashMap<String, Class<? extends Entity>> classes = new HashMap<String, Class<? extends Entity>>() {{
        put("faculties", Faculty.class);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        FacultyStorage facs = new FacultyStorage();
        facs.load();

        for(Entity e : facs.entities) {
            System.out.println(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == '-') { // command

            } else {

            }
        }
    }
}