package seedu.hdbuy.common.exception;

public class InvalidInputException extends Exception {

    public InvalidInputException(String input) {
        super(input + " is not a valid command.\nEnter \"help\" to see valid commands");
    }
}
