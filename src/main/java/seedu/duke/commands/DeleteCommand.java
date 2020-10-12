package seedu.duke.commands;

import seedu.duke.utility.ShowList;

public class DeleteCommand {
    String showName;

    public DeleteCommand(String showName) {
        this.showName = showName;
    }

    public void delete(String showToBeDeleted) {
        ShowList.getShowList().remove(showToBeDeleted);
    }
}
