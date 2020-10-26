package seedu.duke.writinglist;

import org.junit.jupiter.api.Test;
import seedu.duke.writing.WritingList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WritingListTest {
    private static WritingList writings;

    private static void initializeTestDatabase() {
        writings.addPoem("fantasy", 1, "thih", "sdfa", "jdkfa");
        writings.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        writings.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        writings.addEssay("fantasy", 23, "thih", "sdfa", "jdkfa");
        writings.addEssay("fantasy", 13, "thih", "sdfa", "jdkfa");
    }

    @Test
    public void removeAllWritings_getWritingSize() {
        writings.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, writings.getWritingSize());
        writings.clearAll(writings);
        assertEquals(0, writings.getWritingSize());
    }

    @Test
    public void testRemoveID() {
        writings.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, writings.getWritingSize());
        //writings.removeID(12);
        //assertEquals(0, writings.getWritingSize());
    }
}

