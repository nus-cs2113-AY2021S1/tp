package seedu.duke.bunnylist;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.bunnyexceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.bunnyexceptions.CommandInvalidException;
import seedu.duke.exceptions.bunnyexceptions.CommandMissingArgumentsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunnyListTest {
    public static void initializeBunnyListTestDatabase() {
        try {
            BunnyList.addBunny("bunny i\\ cool idea g\\ cool");
            BunnyList.addBunny("bunny i\\ some fun idea g\\ fun");
            BunnyList.addBunny("bunny i\\ kind of awesome idea g\\ awesome");
            BunnyList.addBunny("bunny i\\ some random idea g\\ awesome");
            BunnyList.addBunny("bunny i\\ funny idea g\\ fun");
            BunnyList.addBunny("bunny i\\ some strange idea g\\ mystery");
            BunnyList.addBunny("bunny i\\ idea example g\\ unknown");
        } catch (CommandMissingArgumentsException | BunnyIdeaMissingException | CommandInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    void numBunny_checkCountValue_sevenEntriesTest() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        assertEquals(7, BunnyList.numBunny());
    }

    @Test
    void clearAllBunny_clearWholeList_returnListSizeZero() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        assertEquals(7, BunnyList.numBunny());
        BunnyList.clearAllBunny();
        assertEquals(0, BunnyList.numBunny());
    }
}