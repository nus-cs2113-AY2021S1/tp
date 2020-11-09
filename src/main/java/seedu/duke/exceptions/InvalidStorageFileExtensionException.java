package seedu.duke.exceptions;

public class InvalidStorageFileExtensionException extends DukeException {
    @Override
    public String getMessage() {
        return "Please indicate a .json file";
    }
}
