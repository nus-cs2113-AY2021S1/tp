package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.classes.WatchTime;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateTimeLimitCommandTest {

    public static UpdateTimeLimitCommand updateObject;


    @org.junit.jupiter.api.BeforeAll
    public static void main() {
        WatchTime limitation = new WatchTime(java.time.LocalDate.of(2020,10,19),60,120);
        assertEquals(WatchTime.getDailyWatchLimit(), 120);

    }


    @Test
    void minutesTest() {
        ArrayList<String> inputExample = new ArrayList<>(Arrays.asList("updatetimelimit", "420"));
        updateObject = new UpdateTimeLimitCommand("updatetimelimit", inputExample);
        updateObject.processCommand();
        assertEquals(420,WatchTime.getDailyWatchLimit());
    }

    @Test
    void hoursAndMinutesTest() {
        ArrayList<String> inputExample = new ArrayList<>(Arrays.asList("updatetimelimit", "2h30m"));
        updateObject = new UpdateTimeLimitCommand("updatetimelimit", inputExample);
        updateObject.processCommand();
        assertEquals(150,WatchTime.getDailyWatchLimit());
    }
}