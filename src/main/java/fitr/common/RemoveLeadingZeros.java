package fitr.common;

import static fitr.common.Messages.EMPTY_STRING;

public class RemoveLeadingZeros {
    public static String removeLeadingZeros(String number) {
        String strPattern = "^0+(?!$)";
        number = number.replaceAll(strPattern, EMPTY_STRING);
        return number;
    }
}
