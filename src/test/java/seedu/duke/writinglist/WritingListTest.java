package seedu.duke.writinglist;

import org.junit.jupiter.api.Test;
import seedu.duke.database.ClearLoader;
import seedu.duke.writing.WritingList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WritingListTest {
    private static WritingList writings;

    private static void initializeTestDatabase() {
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 20, "thih", "sdfa", "jdkfa");
    }

    @Test
    public void clearAll_getTheNumberOfItemsAfterDeleted() {
        WritingList.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, WritingList.getWritingSize());
        WritingList.clearAll(writings);
        assertEquals(0, WritingList.getWritingSize());
    }
}

