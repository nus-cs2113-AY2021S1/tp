package seedu.duke.filters;

import org.junit.jupiter.api.Test;
import seedu.duke.bunnylist.BunnyList;
import seedu.duke.exceptions.BunnyIdeaMissingException;
import seedu.duke.exceptions.CommandMissingArgumentsException;

import static org.junit.jupiter.api.Assertions.*;

class BunnyFilterTest {

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
    void filterBunny_filterIdeaTerm_getOneResult() {
        FilterExecutor.executeFilterCommand("filter bunny i\\ fun");


    }
}