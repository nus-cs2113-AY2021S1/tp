package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddReviewCommandTest {

    @Test
    void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes,20);
        ShowList.setShow("friends",show);
        ShowList.getShow("friends").setReview("very funny");
        ShowList.getShow("friends").setRating(10);

        AddReviewCommand addReviewAndRating = new AddReviewCommand("addreview friends 10 /very funny");
        assertEquals(10,ShowList.getShow("friends").getRating());
        assertEquals("very funny",ShowList.getShow("friends").getReview());


    }


}