package seedu.duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.classes.Show;
import seedu.duke.utility.ShowList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class AddCommandTest {

    public static AddCommand updateObject;

    @BeforeAll
    public static void main() {
        new ShowList();
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("example", 3, episodes,29);
        ShowList.setShow("example", show);
        assertEquals(show, ShowList.getShow("example"));
        String[] inputExample = {"add", "example",  "3", "21,10,12","20"};
        updateObject = new AddCommand(inputExample);
    }

    @Test
    public void processCommand() {
        final InputStream sysInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("yes".getBytes());
        System.setIn(in);
        AddCommand.processCommand();
        System.setIn(sysInBackup);
        assertEquals(21, ShowList.getShow("example").getEpisodesForSeason(1));

    }
}
