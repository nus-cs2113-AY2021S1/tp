package seedu.duke.commands;

import seedu.duke.utility.ShowList;
import seedu.duke.classes.Show;

public class AddReviewCommand extends Command {

    public static void AddReviewCommand (String input) {
        String[] tokenizedInput = input.split(" ");
        Show s = ShowList.getShow(tokenizedInput[1]);
        s.setRating(Integer.parseInt(tokenizedInput[2]));
        s.setReview(String.join(" ", tokenizedInput[3]));
    }
}
