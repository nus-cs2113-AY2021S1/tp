package seedu.commons;

public class Util {

    public static String GeneratePadStringWithCharAndLength(char pad, int length) {
        if (length == 0) {
            return "";
        } else {
            return String.format("%" + length + "s", "").replace(' ', pad);
        }
    }

    public static String limitStringWithDots(String string, int limit) {
        // Could be changed to -4 to get an extra space
        if (limit <= 0) {
            return "";
        } else if (limit < 3) {
            return string.substring(0, limit);
        }
        return (string.length() > limit) ? (string.substring(0, limit - 3) + "...") : string;
    }
}
