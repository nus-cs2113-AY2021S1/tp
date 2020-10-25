package seedu.duke.bunnylist;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.CommandMissingArgumentsException;
import seedu.duke.wordlist.WordList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunnyListTest {
    private static void initializeBunnyListTestDatabase() {
        try {
            BunnyList.addBunny("bunny i\\ some cool idea g\\ cool");
            BunnyList.addBunny("bunny i\\ some fun idea g\\ fun");
            BunnyList.addBunny("bunny i\\ some kind of idea g\\ awesome");
        } catch (CommandMissingArgumentsException | BunnyIdeaMissingException e) {
            e.printStackTrace();
        }
    }


    @Test
    void numBunny() {
        BunnyList.bunniesList.clear();
        initializeBunnyListTestDatabase();
        assertEquals(3, BunnyList.numBunny());
    }
}