package seedu.duke.util;

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
}
