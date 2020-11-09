package seedu.duke.utility;

import static seedu.duke.utility.Ui.TAB_INDENTATION;

/**
 * Handles the various exceptions which may be encountered during the program's lifetime.
 * Some are slightly unnecessary but were included for the 'personality' of the Duke Bot
 */
public class ErrorHandling extends Exception {

    public enum ExceptionResponse {
        EXCEPTION_NO_DESCRIPTION(TAB_INDENTATION + "No description"),
        EXCEPTION_NO_TIME_DATA(TAB_INDENTATION + "No time"),
        EXCEPTION_UNIDENTIFIED_INPUT(TAB_INDENTATION + "Please input something"),
        EXCEPTION_IO_EXCEPTION(TAB_INDENTATION + "IO Error"),
        EXCEPTION_INVALID_SEARCH_DATE(TAB_INDENTATION + "When is that???!!! Please put in a proper date"
                + " in the format : 'yyyy-mm-dd'"),
        EXCEPTION_NOT_FOUND_EXCEPTION(TAB_INDENTATION + "Hey buddy, it seems like we don't have what "
                + "you're looking for!"),
        EXCEPTION_INVALID_INPUT(TAB_INDENTATION + "I don't know what you just said. Please input a valid number/name. "
                + "Try 'example' for help with the command format."),
        EXCEPTION_INVALID_FORMAT(TAB_INDENTATION + "It seems like the format of the command is wrong, "
                + "Try 'help' for an explanation of the commands,\n\tor 'example' for assistance with command format."),
        EXCEPTION_CREATE_FILE_ERROR(TAB_INDENTATION + "Error creating file."),
        EXCEPTION_INVALID_EPISODES_INPUT_EXCEPTION(TAB_INDENTATION + "The number of episodes input doesn't match "
                + "the number of seasons. The show was not added"),
        EXCEPTION_INVALID_ADDING_NAME_FORMAT_EXCEPTION(TAB_INDENTATION + "If the name of your show contains more "
                + "than one word you will need to input the name with no spaces.\n\tExample: For show name "
                + "'Running Man' try `RunningMan` or `Running_Man`."),
        EXCEPTION_INVALID_RATING_INPUT(TAB_INDENTATION + "Sorry! This is an invalid rating! Try again!"),
        EXCEPTION_INPUT_LARGER_THAN_EXPECTED(TAB_INDENTATION + "Sorry! It seems like you have entered the "
                + "wrong number! Try again!"),
        EXCEPTION_EXTRA_WHITE_SPACE(TAB_INDENTATION + "Sorry! It seems like you have input an extra white"
                + " space in your command.Try again."),
        EXCEPTION_INVALID_TIME_INPUT(TAB_INDENTATION + "Sorry! It seems like you have entered the wrong time format."),
        EXCEPTION_EMPTY_REVIEW(TAB_INDENTATION + "There is no review for this show!");



        private String exception;

        ExceptionResponse(String exceptionMessage) {
            this.exception = exceptionMessage;
        }

        @Override
        public String toString() {
            return exception;
        }
    }

    public ErrorHandling(ExceptionResponse exception) {
        super(exception.toString());
    }
}
