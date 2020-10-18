package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class ChangeRatingCommand extends Command{
    String showName;

    public ChangeRatingCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Changes a rating of a show the user requests to change.
     *
     * @param showName name of show which rating is to be changed
     * @param targetRating  new rating to update
     */

    public void changeRating(String showName, int targetRating) throws NullPointerException,IndexOutOfBoundsException {
        if (ShowList.getShowList().containsKey(showName)) {
            if (targetRating < 0 || targetRating > 10) {
                throw new IndexOutOfBoundsException();
            } else {
                Show show = ShowList.getShow(showName);
                show.setRating(targetRating);
                ShowList.setShow(showName, show);
            }
        } else {
            throw new NullPointerException();
        }
    }
}
