package seedu.duke.commands;

import seedu.duke.utility.ErrorHandling;
import seedu.duke.utility.ShowList;

public class DeleteCommand extends Command {
    String showName;

    public DeleteCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Deletes a show.
     *
     * @param showName show to be deleted
     * @throws NullPointerException throws exception when show is not in the list
     */
    public static void delete(String showName) throws NullPointerException {
        if (ShowList.getShowList().containsKey(showName)) {
            ShowList.getShowList().remove(showName);
        } else {
            throw new NullPointerException();
        }
    }
}
