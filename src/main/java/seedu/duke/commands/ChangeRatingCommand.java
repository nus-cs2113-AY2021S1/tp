package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class ChangeRatingCommand {
    String showName;

    public ChangeRatingCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Changes a rating of a show the user requests to change
     *
     * @param showName name of show which rating is to be changed
     * @param targetRating  new rating to update
     */

    public void changeRating(String showName, int targetRating) {
        Show show = ShowList.getShow(showName);
        show.setRating(targetRating);
        ShowList.setShow(showName, show);
        /**
         * Process :
         * get the Show object that you want to change
         * edit the object (referenced by copy not by pointer)
         * put the edited version back into the showlist
         */
    }
}
