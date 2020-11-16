package seedu.financeit.utils;

public class TestInputGenerator {
    public static final String MANUAL_TRACKER_COMMAND_STRING_PREFIX = "ledger";
    public static final String ENTRY_TRACKER_COMMAND_STRING = "entry";

    public static String generateCommand(String commandStringPrefix, String commandType, String[][] params) {
        String output = commandStringPrefix + " " + commandType;
        for (String[] param : params) {
            output += " " + param[0] + " ";
            output += " " + param[1] + " ";
        }
        return output;
    }
}
