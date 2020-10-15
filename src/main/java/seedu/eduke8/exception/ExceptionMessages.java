package seedu.eduke8.exception;

public class ExceptionMessages {
    public static final String ERROR_STORAGE_FAIL = "Oh no! An error has occurred when accessing the file."
            + "Please check again!";
    public static final String ERROR_UNKNOWN = "Oh no! An unknown error has occurred.";
    public static final String ERROR_UNRECOGNIZED_COMMAND = "Oops! The command cannot be understood."
            + System.lineSeparator() + "Please enter in \"help\" to get the list of available commands. ";
    public static final String ERROR_QUIZ_WRONG_FORMAT = "Invalid command! The command for quiz is as follows:"
            + System.lineSeparator() + "quiz t/<topic> n/<number of questions>" + System.lineSeparator()
            + "(Topics available can be found using the \"topics\" command)";
    public static final String ERROR_QUIZ_COMMAND_NOT_IMPLEMENTED = "Oh! This command has yet to be implemented. "
            + System.lineSeparator()
            + "Stay tune for Version 2.0!";
    public static final String ERROR_QUIZ_ANSWER_NOT_INDEX = "Please choose the answer using its index (1,2,3 or 4)!";
    public static final String ERROR_QUIZ_INVALID_QUESTION_NUMBER = "The number of quiz questions"
            + " must be more than 1!";
    public static final String ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS =
            "There is not enough questions in the topic for the quiz!";
}
