package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RatingCommandTest {

    @Test
    void rateShow() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes, 20);
        ShowList.setShow("friends", show);
        ShowList.getShow("friends").setRating(5);
        assertEquals(5, ShowList.getShow("friends").getRating());

    }
}