package seedu.eduke8.exception;

public class ExceptionMessages {
    public static final String ERROR_STORAGE_FAIL = "Oh no! An error has occurred when accessing the file."
            + "Please check again!";
    public static final String ERROR_UNRECOGNIZED_COMMAND = "Oops! The command cannot be understood."
            + System.lineSeparator() + "Please enter in \"help\" to get the list of available commands.";
    public static final String ERROR_QUIZ_WRONG_FORMAT = "Invalid command! The command for quiz is as follows:"
            + System.lineSeparator() + "quiz t/<topic> n/<number of questions> s/<time given to complete 1 question>"
            + System.lineSeparator() + "(Topics available can be found using the \"topics\" command)";
    public static final String ERROR_QUIZ_ANSWER_NOT_INDEX = "Please choose the answer using its index (1,2,3 or 4)!";
    public static final String ERROR_QUIZ_ANSWER_INDEX_OUT_OF_BOUNDS =
            "Please choose only options 1, 2, 3 or 4";
    public static final String ERROR_QUIZ_INVALID_QUESTION_NUMBER = "The number of quiz questions"
            + " must be more than 0!";
    public static final String ERROR_QUIZ_INSUFFICIENT_TOPIC_QUESTIONS =
            "There is not enough questions in the topic for the quiz!";
    public static final String ERROR_TOPIC_DOES_NOT_EXIST = "No such topic exists, did you spell it correctly?";
    public static final String ERROR_QUESTION_DOES_NOT_EXIST = "No such question exists, did you spell it correctly?";
    public static final String ERROR_OPTION_DOES_NOT_EXIST = "No such option exists, did you spell it correctly?";
    public static final String ERROR_NO_RIGHT_ANSWER = "Error with question: No right answer specified";
    public static final String ERROR_NOTE_WRONG_FORMAT = "Invalid command! The command for note is as follows:"
            + System.lineSeparator() + "'note add' or 'note delete' or 'note list'";
    public static final String ERROR_QUIZ_TIMER_NEGATIVE = "Please choose a timer that is greater than 0!";
}
