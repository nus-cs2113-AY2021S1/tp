package seedu.financeit.common.exceptions;

public class FolderNotFoundException extends Exception {
    public FolderNotFoundException() {
        super("Folder not found");
    }
}
