package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class DeleteRatingCommand extends Command {
    String showName;

    public DeleteRatingCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Deletes a rating of a show.
     *
     * @param showName show which rating is to be deleted
     * @throws NullPointerException throws exception when show is not in the list
     */
    public void deleteRating(String showName) throws NullPointerException {
        if (ShowList.getShowList().containsKey(showName)) {
            Show show = ShowList.getShow(showName);
            show.setRating(-1);
            ShowList.setShow(showName,show);
        } else {
            throw new NullPointerException();
        }
    }
}
