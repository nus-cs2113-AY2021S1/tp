package seedu.duke.database;

import org.junit.jupiter.api.Test;
import seedu.duke.writing.WritingList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClearLoaderTest {
    private static WritingList writing;

    private static void initializeTestDatabase() {
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa");
    }

    @Test
    public void testGetType_testGetItem() {
        WritingList.clearAll(writing);
        initializeTestDatabase();
        //ClearLoader.clearItems("clear type\\writing item\\3", writing);
        //assertEquals(4, writing.getCountWritings());
        assertEquals("writing", ClearLoader.getType("type\\writing"));
        assertEquals("3", ClearLoader.getItem("item\\3"));
    }
}
