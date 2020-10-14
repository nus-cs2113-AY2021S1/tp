package seedu.duke;

import seedu.duke.DukeException;

public class DukeMemberListEmptyException extends DukeException {

    public DukeMemberListEmptyException() {
        printMessage();
    }

    public void printMessage() {
        System.out.println("OOPS!!! The member list is empty!\n");
    }
}
