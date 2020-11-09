package common;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String LINE = "---------------------------------------------------------------------";

    public static final String ADMIN = "admin";
    public static final String MODULE = "module";
    public static final String CHAPTER = "chapter";
    public static final String CARD = "flashcard";

    public static final String MESSAGE_HELP = "[Type help to view a list of commands available]";
    public static final String MESSAGE_INVALID_ACCESS = "Sorry, you are currently at %1$s level"
            + ", please go to %2$s level first.";
    public static final String MESSAGE_MISSING_ARGS = "The arguments are missing.\n";
    public static final String MESSAGE_INCORRECT_ACCESS = "%s command can only be called at admin, "
            + "module and chapter level.";
    public static final String MESSAGE_INCORRECT_ACCESS_AT_CHAPTER_LEVEL = "%s command can only be called at admin, "
            + "module.";
    public static final String MESSAGE_EXTRA_ARGS = "There should not be any arguments for %s.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "The format for %s command is wrong.\n";
    public static final String MESSAGE_MISSING_INDEX = "The index for %s is missing.\n";
    public static final String MESSAGE_NON_INTEGER = "The index for %s should be an integer.\n";
    public static final String MESSAGE_INVALID_INDEX_RANGE = "The %1$s index needs to be within the range "
            + "of the total number of %1$ss.\n";
    public static final String MESSAGE_NO_QUESTION_OR_ANSWER = "The content for question / answer is empty.\n";
    public static final String MESSAGE_NO_QUESTION_AND_ANSWER = "The content for question and answer are both empty.\n";
    public static final String MESSAGE_DATE_FORMAT = "The date should be in the format of yyyy-MM-dd.\n";
    public static final String MESSAGE_INVALID_COMMAND_TYPE = "There is no such command type.\n";
    public static final String MESSAGE_ITEM_EXISTED = "The %1$s %2$s already exists, the %3$s cannot be duplicated.\n";
    public static final String MESSAGE_ALPHANUMERIC_CHARACTERS = "Only alphanumeric characters and spaces "
            + "are allowed for %1$s.\n";
}
