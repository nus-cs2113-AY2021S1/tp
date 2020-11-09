package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

//@@author bryanbeh1998

public class DeleteReviewCommand extends Command {
    String showName;

    public DeleteReviewCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Deletes review of a show.
     * @param showName Name of show which review is to be deleted
     * @throws NullPointerException throws exception when show is not in the list
     * @throws IndexOutOfBoundsException throws exception when there is no review
     */
    public void deleteReview(String showName) throws NullPointerException,IndexOutOfBoundsException {
        if (ShowList.getShowList().containsKey(showName)) {
            Show show = ShowList.getShow(showName);
            if (show.getReview().equals("null")) {
                throw new IndexOutOfBoundsException();
            } else {
                show.setReview("null");
                ShowList.setShow(showName,show);
            }
        }
    }
}
