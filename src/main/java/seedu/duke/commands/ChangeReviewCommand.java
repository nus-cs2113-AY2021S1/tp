package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

//@@author bryanbeh1998

public class ChangeReviewCommand extends Command {
    String showName;

    public ChangeReviewCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Changes a review of a show.
     * @param showName Name of show which review is to be changed
     * @param review New review to be updated
     * @throws NullPointerException throws exception when show is not in the list
     */
    public void changeReview(String showName,String review) throws NullPointerException {

        Show s = ShowList.getShow(showName);
        s.setReview(review);
        ShowList.setShow(showName,s);

    }

}
