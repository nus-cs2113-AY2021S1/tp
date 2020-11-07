package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeReviewCommandTest {

    @Test
    void changeReview() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes,20);
        ShowList.setShow("friends",show);
        ShowList.getShow("friends").setReview("Not so funny");
        ChangeReviewCommand updateReview = new ChangeReviewCommand("friends");
        updateReview.changeReview("friends","very very funny");
        assertEquals("very very funny",ShowList.getShow("friends").getReview());
    }
}