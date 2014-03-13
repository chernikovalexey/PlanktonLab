package lab;

/**
 * Created by Alexey
 * At 10:30 PM on 3/13/14
 */

public class Validator {
    public static boolean isEmpty(String str) {
        return str.trim().isEmpty();
    }

    public static boolean hasOnlyWords(String str) {
        for (int i = 0, len = str.length(); i < len; ++i) {
            if (!Character.isLetter(str.charAt(i)) || !Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}