package seedu.duke.exceptions;

//@@author killingbear999
public class InvalidStorageFilePathException extends DukeException {
    @Override
    public String getMessage() {
        return "Sorry, the file path is invalid.";
    }
}
