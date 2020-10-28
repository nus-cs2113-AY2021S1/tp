package seedu.financeit.common.exceptions;

public class InfoTextIndexOutOfRangeException extends Exception {
    public InfoTextIndexOutOfRangeException() {
        super("Number does not correspond to any item on the list");
    }
}
