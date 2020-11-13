package seedu.duke.commands;

import seedu.duke.classes.Show;
import seedu.duke.classes.WatchTime;
import seedu.duke.utility.ShowList;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WatchCommandTest {

    public static WatchCommand updateObject;


    @org.junit.jupiter.api.BeforeAll
    public static void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes,20);
        ShowList.setShow("friends", show);
        assertEquals(show, ShowList.getShow("friends"));

        WatchTime limitation = new WatchTime(java.time.LocalDate.of(2020,10,19),0,120);
        assertEquals(WatchTime.getDailyWatchLimit(), 120);
        ArrayList<String> inputExample = new ArrayList<>(Arrays.asList("watch", "friends"));
        updateObject = new WatchCommand("watch", inputExample);
    }


    @org.junit.jupiter.api.Test
    void processCommand() {
        updateObject.processCommand();
        assertEquals(2, ShowList.getShow("friends").getCurrentEpisode());
        assertEquals(WatchTime.getDurationWatchedToday(), 20);
    }
}