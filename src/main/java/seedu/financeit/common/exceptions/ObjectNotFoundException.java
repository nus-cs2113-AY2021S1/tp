package seedu.financeit.common.exceptions;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String object) {
        super(String.format("The object [%s]cannot be found!"));
    }
}
