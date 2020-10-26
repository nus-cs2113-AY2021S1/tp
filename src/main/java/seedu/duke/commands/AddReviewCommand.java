package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

public class AddReviewCommand extends Command {

    public AddReviewCommand(String input) throws ArrayIndexOutOfBoundsException,NullPointerException,
            IndexOutOfBoundsException {
        String[] tokenizedInput = input.split(" ");
        int indexOfSlash = input.indexOf("/");
        int rating = Integer.parseInt(tokenizedInput[2]);
        String review = input.substring(indexOfSlash + 1);

        if (tokenizedInput.length < 3) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (rating < 0 || rating > 10) {
            throw new IndexOutOfBoundsException();
        }

        String showName = tokenizedInput[1];
        Show s = ShowList.getShow(showName);
        s.setRating(rating);
        s.setReview(review);
        ShowList.setShow(showName,s);
        Ui.printReviewAdded(showName);
    }
}
