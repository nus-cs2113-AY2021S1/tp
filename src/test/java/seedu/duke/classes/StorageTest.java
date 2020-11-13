package seedu.duke.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.utility.ShowList;
import seedu.duke.utility.Ui;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StorageTest {

    public static Storage storage;
    public static ShowList shows;

    @BeforeAll
    public static void main() {
        shows = new ShowList();
        storage = new Storage(Ui.SAVE_DIRECTORY);
        int[] episodes = new int[]{21, 10, 12};
        Show show = new Show("friends", 3, episodes, 20);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}