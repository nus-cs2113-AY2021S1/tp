package seedu.duke.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.UpdateShowEpisodeProgressCommand;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


class StorageTest {

    public static Storage storage;
    public static ShowList shows;

    @BeforeAll
    public static void main() {
        shows = new ShowList();
        storage = new Storage(Ui.SAVE_DIRECTORY);
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes);
        shows.setShow("friends", show);
        assertEquals(show, shows.getShow("friends"));
    }


    @Test
    void saveState() {
        try {
            storage.saveState();
            assertEquals(shows.getShow("friends").toString(), storage.loadState().getShow("friends").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}