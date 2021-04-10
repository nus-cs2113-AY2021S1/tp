package seedu.hdbuy.common.exception;

public class InvalidParameterException extends Exception {
    private final String keyCommand;

    public InvalidParameterException(String keyCommand) {
        super("This is an invalid command. Please try the following:");
        this.keyCommand = keyCommand;
    }

    public String getKeyCommand() {
        return keyCommand;
    }
}
