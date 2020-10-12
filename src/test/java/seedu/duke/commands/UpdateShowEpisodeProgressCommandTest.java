package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateShowEpisodeProgressCommandTest {
    public static UpdateShowEpisodeProgressCommand updateObject;


    @BeforeAll
    public static void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes);
        ShowList.setShow("friends", show);
        assertEquals(show, ShowList.getShow("friends"));
        java.util.ArrayList<String> inputExample = new java.util.ArrayList<>(Arrays.asList("episode", "friends", "3"));
        updateObject = new UpdateShowEpisodeProgressCommand("episode", inputExample);
    }

    @Test
    void processCommand() {
        updateObject.processCommand();
        assertEquals(3, ShowList.getShow("friends").getCurrentEpisode());
    }
}