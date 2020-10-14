package seedu.duke;

public class DukeMemberNotFoundException extends DukeException {

    public void DukeMemberNotFoundException() {
        printMessage();
    }

    public void printMessage() {
        System.out.println("OOPS!!! The member does not exist.\n");
    }
}
