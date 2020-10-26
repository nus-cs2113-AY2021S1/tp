package seedu.duke.writinglist;

import org.junit.jupiter.api.Test;
import seedu.duke.writing.WritingList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WritingListTest {
    private static WritingList writings;

    private static void initializeTestDatabase() {
        WritingList.addPoem("fantasy", 1, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 23, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 13, "thih", "sdfa", "jdkfa");
    }

    @Test
    public void removeAllWritings_getWritingSize() {
        WritingList.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, WritingList.getWritingSize());
        WritingList.clearAll(writings);
        assertEquals(0, WritingList.getWritingSize());
    }

    @Test
    public void testRemoveID() {
        WritingList.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, WritingList.getWritingSize());
        //writings.removeID(12);
        //assertEquals(0, writings.getWritingSize());
    }
}

