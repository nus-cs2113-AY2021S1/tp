package seedu.duke.writinglist;

import org.junit.jupiter.api.Test;
import seedu.duke.writing.WritingList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WritingListTest {
    private static void initializeTestDatabase() {
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa");
    }

    @Test
    public void removeAllWritings_getWritingSize() {
        WritingList.writinglist.clear();
        initializeTestDatabase();
        assertEquals(5, WritingList.getWritingSize());
        WritingList.clearAll();
        assertEquals(0, WritingList.getWritingSize());
    }
}

