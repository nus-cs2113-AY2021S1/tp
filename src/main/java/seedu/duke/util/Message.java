package seedu.duke.util;

import java.io.File;

public class Message {
    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "SUCCESS!! The module has been updated.\n";
    public static final String MESSAGE_EMPTY_INPUT = "Please enter a command.\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Sorry, the command entered contains some unrecognised parameters starting from here:\n";
    public static final String MESSAGE_CHECK_COMMAND_FORMAT =
            "Please check to make sure to follow the command format as such:\n";
    public static final String MESSAGE_NO_EDIT_MODULE = "Please enter a new module code to edit.\n";

    public static String messageAddTaskSuccess(String taskDescription) {
        return String.format("SUCCESS!! Task %s is created.\n", taskDescription);
    }

    public static final String DUCK_LOGIN = "Duck login";
    public static final String INCORRECT_USER_OR_PW = "Incorrect user or pw.";

    public static final String MESSAGE_FILE_OPERATION_IO_ERROR = "Error writing to file: %s";
    public static final String JSON_FILE_PATH =
            "src"
            + File.pathSeparator +"main"
            + File.pathSeparator +"data"
            + File.pathSeparator +"taskManager.json";

}
