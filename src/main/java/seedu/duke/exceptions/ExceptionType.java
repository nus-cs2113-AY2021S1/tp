package seedu.duke.exceptions;

public enum ExceptionType {
    INVALID_COMMAND("I do not understand!!"),
    NO_ROUTE_DELIMITER("Oops! You are missing the delimiter."),
    NO_LOCATIONS("Oh dear! I don't have all locations."),

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
