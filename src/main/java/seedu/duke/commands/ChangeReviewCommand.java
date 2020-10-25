package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

public class ChangeReviewCommand extends Command {
    String showName;

    public ChangeReviewCommand(String showName) {
        this.showName = showName;
    }

    public void changeReview(String showName,String review) throws NullPointerException {

        Show s = ShowList.getShow(showName);
        s.setReview(review);
        ShowList.setShow(showName,s);

    }

}
