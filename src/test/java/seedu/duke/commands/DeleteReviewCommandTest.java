package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import static org.junit.jupiter.api.Assertions.*;

class DeleteReviewCommandTest {

    @Test
    void deleteReview() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes,20);
        ShowList.setShow("friends",show);
        ShowList.getShow("friends").setReview("Funny");
        DeleteReviewCommand deleteReview = new DeleteReviewCommand("friends");
        deleteReview.deleteReview("friends");
        assertEquals("null",ShowList.getShow("friends").getReview());
    }
}