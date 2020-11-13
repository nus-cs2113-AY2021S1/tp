package seedu.duke.bunnylist;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.bunnyexceptions.BunnyIndexOutOfBoundsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.bunnylist.BunnyList.bunniesList;
import static seedu.duke.bunnylist.BunnyListTest.initializeBunnyListTestDatabase;

class DeleteBunnyTest {

    @Test
    void deleteBunny_deleteSecondBunnyInList_getTwoBunny() {
        bunniesList.clear();
        initializeBunnyListTestDatabase();
        assertEquals(7, BunnyList.numBunny());
        try {
            DeleteBunny.deleteBunny("delete bunny 2", bunniesList);
            DeleteBunny.deleteBunny("delete bunny 3", bunniesList);
        } catch (BunnyIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        assertEquals(5, BunnyList.numBunny());
    }

    @Test
    void getNumFromInput_parseIntFromInput_getIntValue() {
        try {
            assertEquals(3, DeleteBunny.getNumFromInput("delete bunny 3", 5));
        } catch (BunnyIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}