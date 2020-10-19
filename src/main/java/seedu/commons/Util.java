package seedu.commons;

public class Util {

    public static String padString(char pad, int length) {
        return String.format("%1$" + length + "s", "").replace(' ', pad);
    }
}
