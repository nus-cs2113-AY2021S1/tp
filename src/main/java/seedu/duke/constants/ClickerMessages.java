package seedu.duke.constants;

public class ClickerMessages {
    public static final String NEWLINE = System.lineSeparator();

    /** Greeting message formats. */
    public static final String FAREWELL_GREETING =
            "Bye %1$s! Hope to see you again soon!" + NEWLINE;
    public static final String HELLO_GREETING =
            "Hello %1$s! Welcome to Fluffle!" + NEWLINE + "What can I do for you?" + NEWLINE;

    /**
     * Settings loader messages.
     */
    public static final String ERROR_READING_FILE_ON_LINE_MSG_FORMAT =
            "Error reading settings file! Error on line:" + NEWLINE + "%1$s";
    public static final String LOADING_SETTINGS_MSG = "Loading userSettings.txt save file...";
    public static final String SETTINGS_FILE_EMPTY_MSG = "userSettings.txt save file empty" + NEWLINE
            + "No previous settings loaded";
    public static final String SETTINGS_FILE_NOT_FOUND_MSG = "userSettings.txt save file not found" + NEWLINE
            + "Creating new file...";
    public static final String LOADING_HISTORY_MSG = "Loading history.txt save file...";
    public static final String HISTORY_FILE_EMPTY_MSG = "history.txt save file empty" + NEWLINE
            + "No previous settings loaded";
    public static final String HISTORY_FILE_NOT_FOUND_MSG = "history.txt save file not found" + NEWLINE
            + "Creating new file...";

    /** File creator function messages.  */
    public static final String NEW_FILE_CREATED_MSG_FORMAT = "New file created";
    public static final String IO_ERROR_WHEN_MAKING_FILE_MSG = "IO error when making file!";
    public static final String FILE_ALREADY_EXISTS_MSG = "File already exists.";
    public static final String FILE_CREATED_PATH_MSG = "File created";
    public static final String DIRECTORY_CREATED_SUCCESSFULLY_MSG = "Directory created successfully";
    public static final String COULD_NOT_CREATE_DIRECTORY_MSG = "Sorry, could not create specified directory";
    public static final String FILE_PATH_TO_DIRECTORY_INVALID_MSG = "File path to directory invalid!";
    public static final String FILE_NOT_FOUND_MSG = "File does not exist.";
    public static final String FILE_AUTO_CREATED_MSG = "Auto creating new file";

    /** New word added messages. */
    public static final String ADD_NOUN_MSG = "Added the following noun:";
    public static final String ADD_VERB_MSG = "Added the following verb:";
    public static final String ADD_ADJECTIVE_MSG = "Added the following adjective:";
    public static final String LIST_WORDS_MSG = "Here are the words stored in the program:";

    /** New bunny added messages. */
    public static final String ADD_BUNNY_MSG = "New bunny added!";
    public static final String LIST_BUNNY_MSG = "Here are the bunnies stored in the program:";
    public static final String BUNNY_WRONG_FORMAT = "Bunny command wrong format";
    public static final String BUNNY_MISSING_IDEA_MSG = "Bunny command missing idea?";

    /** New character added messages. */
    public static final String ADD_CHARACTER_MSG = "New character added!";
    public static final String LIST_CHARACTER_MSG = "Here are the characters stored in the program:";
    public static final String BUNNY_CHARACTER_FORMAT = "Character command wrong format";

    /** Command not recognised message. */
    public static final String COMMAND_UNRECOGNISED_MSG = "Command not recognised?";


}
