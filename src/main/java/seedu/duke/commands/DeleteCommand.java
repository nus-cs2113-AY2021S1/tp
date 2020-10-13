package seedu.duke.commands;

import seedu.duke.utility.ErrorHandling;
import seedu.duke.utility.ShowList;

public class DeleteCommand {
    String showName;

    public DeleteCommand(String showName) {
        this.showName = showName;
    }

    public void delete(String showToBeDeleted) throws NullPointerException {
        if (ShowList.getShowList().containsKey(showToBeDeleted)) {
            ShowList.getShowList().remove(showToBeDeleted);
        } else {
            throw new NullPointerException();
        }
    }
}
