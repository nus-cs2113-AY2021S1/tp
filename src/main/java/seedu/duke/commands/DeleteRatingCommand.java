package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class DeleteRatingCommand {
    String showName;

    public DeleteRatingCommand(String showName) {
        this.showName = showName;
    }

    public void deleteRating(String showRatingToBeDeleted) throws NullPointerException{
        if (ShowList.getShowList().containsKey(showRatingToBeDeleted)) {
            Show show = ShowList.getShow(showRatingToBeDeleted);
            show.setRating(-1);
            ShowList.setShow(showRatingToBeDeleted,show);
        } else {
            throw new NullPointerException();
        }
    }
}
