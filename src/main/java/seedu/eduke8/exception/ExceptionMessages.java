package seedu.eduke8.exception;

public class ExceptionMessages {
    public static final String ERROR_STORAGE_LOAD_FAIL = "Oh no! An error has occurred when accessing the file."
            + System.lineSeparator() + "Please check that you have the required files for operation."
            + System.lineSeparator() + "If issues persist please follow the instructions in the"
            + " user guide for troubleshooting.";
    public static final String ERROR_STORAGE_SAVE_FAIL = "Error writing to user data file, user data was not saved.";
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
    public static final String ERROR_TOPICS_JSON_PREFACE = "An issue occurred with loading the questions.";
    public static final String ERROR_TOPICS_JSON_QUESTION = "The question '";
    public static final String ERROR_TOPICS_JSON_TOPIC = "' in the topic '";
    public static final String ERROR_TOPICS_JSON_NOT_FOUR_OPTIONS = "' does not have exactly 4 options.";
    public static final String ERROR_TOPICS_JSON_NO_CORRECT = "' has no correct answer.";
    public static final String ERROR_TOPICS_JSON_TOO_MANY_CORRECT = "' should not have more than 1 correct answer.";
}
