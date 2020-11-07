package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

//@@author judowha and bryanbeh1998

public class AddReviewCommand extends Command {

    /**
     * Adds a rating and a review to a show
     * @param input String with show title, rating and review to be inputted
     * @throws ArrayIndexOutOfBoundsException throws exception when input do not have correct no. of arguments
     * @throws NullPointerException throws exception when show not in list
     * @throws IndexOutOfBoundsException throws exception when invalid rating is provided
     */
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
