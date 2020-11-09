package exception;

public class DirectoryIsEmptyException extends Exception {

    String folderName;

    public DirectoryIsEmptyException(String folderName) {
        this.folderName = folderName;
    }


    @Override
    public String getMessage() {
        return folderName + " is empty.";
    }
}
