package seedu.duke.exceptions;

public enum ExceptionType {
    INVALID_COMMAND("I do not understand!!"),
    NO_ROUTE_DELIMITER("Oops! You are missing the delimiter."),
    NO_LOCATIONS("Oh dear! I don't have all locations."),
    SAME_LOCATIONS("You don't need a bus to get there...You are right there!");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
