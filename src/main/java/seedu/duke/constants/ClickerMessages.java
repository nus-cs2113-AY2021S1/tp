package seedu.duke.constants;

public class ClickerMessages {
    public static final String NEWLINE = System.lineSeparator();

    /** Greetings */
    public static final String FAREWELL_GREETING =
            "Bye %1$s! Hope to see you again soon! " + NEWLINE;
    public static final String HELLO_GREETING =
            "Hello %1$s! Welcome to CLIcker!" + NEWLINE + "What can I do for you?" + NEWLINE;

    /**
     * Settings loader messages
     */
    public static final String ERROR_READING_FILE_ON_LINE_MSG_FORMAT =
            "Error reading settings file! Error on line:" + NEWLINE + "%1$s";
    public static final String LOADING_SETTINGS_MSG = "Loading userSettings.txt save file...";
    public static final String SETTINGS_FILE_EMPTY_MSG = "userSettings.txt save file empty" + NEWLINE
            + "No previous settings loaded";
    public static final String SETTINGS_FILE_NOT_FOUND_MSG = "userSettings.txt save file not found" + NEWLINE
            + "Creating new file...";
}
