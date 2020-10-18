package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {

    @Test
    void delete() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes);
        ShowList.setShow("friends",show);
        DeleteCommand toDelete = new DeleteCommand("friends");
        toDelete.delete("friends");
        assertEquals(null,ShowList.getShow("friends"));
    }
}