package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

class AddCommandTest {

    public static AddCommand updateObject;

    @BeforeAll
    public static void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes,29);
        ShowList.setShow("friends", show);
        assertEquals(show, ShowList.getShow("friends"));
        String[] inputExample = {"add", "friends",  "3", "21,10,12"};
        updateObject = new AddCommand(inputExample);
    }

    @Test
    public void processCommand() {
        assertEquals(21, ShowList.getShow("friends").getEpisodesForSeason(1));

    }
}
