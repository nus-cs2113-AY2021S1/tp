package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

//@@author bryanbeh1998

public class ChangeRatingCommand extends Command {
    String showName;

    public ChangeRatingCommand(String showName) {
        this.showName = showName;
    }

    /**
     * Changes rating of a show
     * @param showName Name of show which rating is to be changed
     * @param targetRating New rating to be updated into showlist
     * @throws NullPointerException throws exception when show is not in list
     * @throws IndexOutOfBoundsException throws exception when invalid rating is provided
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
