package seedu.duke.commands;

import seedu.duke.utility.ShowList;
import seedu.duke.classes.Show;

public class AddReviewCommand extends Command {

    public AddReviewCommand (String input) throws ArrayIndexOutOfBoundsException,NullPointerException{
        String[] tokenizedInput = input.split(" ");
        if (tokenizedInput.length < 3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        String showName = tokenizedInput[1];
        Show s = ShowList.getShow(showName);
        s.setRating(Integer.parseInt(tokenizedInput[2]));
        s.setReview(String.join(" ", tokenizedInput[3]));
        ShowList.setShow(showName,s);
    }
}
