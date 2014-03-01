import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alexey
 * At 6:29 PM on 3/1/14
 */

public class Main {
    private ArrayList<String> commands = new ArrayList<String>(){{
        add("addfac");
        add("editfac");
        add("delfac");
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isCommand = true;
        while(scanner.hasNextLine()) {
            String command = scanner.nextLine();

        }
    }
}