package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class EditCommandTest {

    public static EditCommand editObject;

    @BeforeAll
    public static void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes,29);
        ShowList.setShow("friends", show);
        assertEquals(show, ShowList.getShow("friends"));
        String inputExample = "friends";
        editObject = new EditCommand(inputExample);
    }


    @Test
    public void editSeasons() {
        //replace stdin with string of test user input
        final InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("edit friends\nseason 5\ndone".getBytes());
        System.setIn(in);
        EditCommand.processCommand();
        assertEquals(5,ShowList.getShow("friends").getNumSeasons());
        System.setIn(sysInBackup);
    }

    @Test
    public void editEpisodes() {
        final InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("edit friends\nepisode 10,20,30,40,50\ndone".getBytes());
        System.setIn(in);
        EditCommand.processCommand();
        System.setIn(sysInBackup);
        int[] correctAnswer = {10, 20, 30, 40, 50};
        int[] testAnswer;
        try {
            testAnswer = ShowList.getShow("friends").getNumEpisodesForSeasons();
        } catch (NullPointerException e) {
            testAnswer = ShowList.getShow("f.r.i.e.n.d.s").getNumEpisodesForSeasons();
        }
        assertArrayEquals(correctAnswer, testAnswer);
    }

    @Test
    public void editDuration() {
        final InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("edit friends\nduration 25\ndone".getBytes());
        System.setIn(in);
        EditCommand.processCommand();
        assertEquals(25, ShowList.getShow("friends").getEpisodeDuration());
        System.setIn(sysInBackup);
    }

    @Test
    public void editName() {
        final InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("edit friends\nname f.r.i.e.n.d.s\ndone".getBytes());
        System.setIn(in);
        EditCommand.processCommand();
        assertEquals("f.r.i.e.n.d.s",ShowList.getShow("f.r.i.e.n.d.s").getName());
        System.setIn(sysInBackup);
    }
}
