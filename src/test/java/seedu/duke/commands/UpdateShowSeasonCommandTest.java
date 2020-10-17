package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UpdateShowSeasonCommandTest {
    public static UpdateShowSeasonCommand updateObject;

    @BeforeAll
    public static void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes);
        ShowList.setShow("friends", show);
        assertEquals(show, ShowList.getShow("friends"));
    }

    @Test
    void processCommand() {
        ArrayList<String> inputExample = new ArrayList<>(Arrays.asList("season", "friends", "2"));
        updateObject = new UpdateShowSeasonCommand("season", inputExample);
        updateObject.processCommand();
        assertEquals(2, ShowList.getShow("friends").getCurrentSeason());

    }

    @Test
    void processCommandWithEpisodes() {
        ArrayList<String> inputExample2 = new ArrayList<>(Arrays.asList("season", "friends", "3", "4"));
        updateObject = new UpdateShowSeasonCommand("season", inputExample2);
        updateObject.processCommand();
        assertEquals(3, ShowList.getShow("friends").getCurrentSeason());
        assertEquals(4, ShowList.getShow("friends").getCurrentEpisode());
    }
}