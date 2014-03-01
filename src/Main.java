import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Alexey
 * At 6:29 PM on 3/1/14
 */

public class Main {
    private static ArrayList<String> commands = new ArrayList<String>(){{
        add("addfac");
        add("editfac");
        add("delfac");
    }};

    public static HashMap<String, Class<? extends Entity>> classes = new HashMap<String,Class<? extends Entity>>(){{
        put("faculties", Faculty.class);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isCommand = true;
        while(scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if(commands.indexOf(command.substring(1)) > -1) {

            }
        }
    }
}