package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class RatingCommand extends Command {
    String showName;

    public RatingCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Adds rating to a show.
     * @param showName show which is to be rated
     * @param rating rating to be given to show
     * @throws NullPointerException throws exception when show is not in the list
     * @throws IndexOutOfBoundsException throws exception when input is an invalid rating
     */
    public void rateShow(String showName, int rating) throws NullPointerException,IndexOutOfBoundsException {
        if (ShowList.getShowList().containsKey(showName)) {
            if (rating < 0 || rating > 10) {
                throw new IndexOutOfBoundsException();
            } else {
                Show show = ShowList.getShow(showName);
                show.setRating(rating);
                ShowList.setShow(showName,show);
            }
        } else {
            throw new NullPointerException();
        }
    }
}
