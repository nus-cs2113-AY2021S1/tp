package fitr.common;

public class RemoveLeadingZeros {
    public static String removeLeadingZeros(String number) {
        String strPattern = "^0+(?!$)";
        number = number.replaceAll(strPattern, "");
        return number;
    }
}
