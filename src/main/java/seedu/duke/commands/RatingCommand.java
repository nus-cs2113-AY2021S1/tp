package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class RatingCommand {
    String showName;

    public RatingCommand(String showName) {
        this.showName = showName;
    }

    public void rateShow(String showToBeRated, int rating) throws NullPointerException {
        if (ShowList.getShowList().containsKey(showToBeRated)) {
            if (rating < 0 || rating > 10) {
                throw new IndexOutOfBoundsException();
            } else {
                Show show = ShowList.getShow(showToBeRated);
                show.setRating(rating);
                ShowList.setShow(showName,show);
            }
        } else {
            throw new NullPointerException();
        }
    }
}
