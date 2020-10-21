package seedu.duke;

public class UserFavourite {
    private String command;
    private String description;

    public UserFavourite(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }
}
