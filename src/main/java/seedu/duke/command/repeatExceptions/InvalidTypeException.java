package seedu.duke.command.repeatExceptions;

public class InvalidTypeException extends Exception {

    private String typeCategory;
    private String typeItem;
    private String validType;
    private String message;

    public InvalidTypeException(String typeCategory, String typeItem, String validType) {
        this.typeCategory = typeCategory;
        this.typeItem = typeItem;
        this.validType = validType;

        message = typeItem + " is not a valid " + typeCategory + ". " + "Valid types are: " + validType;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
