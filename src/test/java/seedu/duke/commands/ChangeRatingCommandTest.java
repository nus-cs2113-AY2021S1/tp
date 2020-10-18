package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

class ChangeRatingCommandTest {



    @Test
    void changeRating() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes);
        ShowList.setShow("friends",show);
        ShowList.getShow("friends").setRating(5);
        ChangeRatingCommand update = new ChangeRatingCommand("friends");
        update.changeRating("friends", 10);
        assertEquals(10,ShowList.getShow("friends").getRating());
    }
}