package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteRatingCommandTest {

    @Test
    void deleteRating() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes);
        ShowList.setShow("friends",show);
        ShowList.getShow("friends").setRating(5);
        DeleteRatingCommand delete = new DeleteRatingCommand("friends");
        delete.deleteRating("friends");
        assertEquals(-1,ShowList.getShow("friends").getRating());
    }
}