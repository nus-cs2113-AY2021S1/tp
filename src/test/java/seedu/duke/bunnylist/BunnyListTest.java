package seedu.duke.bunnylist;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.CommandInvalidException;
import seedu.duke.exceptions.CommandMissingArgumentsException;
import seedu.duke.wordlist.WordList;

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
    void numBunny() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        assertEquals(7, BunnyList.numBunny());
    }
}