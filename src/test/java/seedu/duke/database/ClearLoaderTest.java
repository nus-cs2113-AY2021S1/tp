package seedu.duke.database;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.WrongClearCommandFormat;
import seedu.duke.writing.WritingList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClearLoaderTest {
    private static WritingList writings;

    private static void initializeTestDatabase() {
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 20, "thih", "sdfa", "jdkfa");
        WritingList.addEssay("fantasy", 20, "thih", "sdfa", "jdkfa");
    }

    @Test
    public void clearAll_getWritingSize() {
        WritingList.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, WritingList.getWritingSize());
        WritingList.clearAll(writings);
        assertEquals(0, WritingList.getWritingSize());
    }

    @Test
    public void removeID_removeAll_noneOfWritingLeft() {
        WritingList.clearAll(writings);
        initializeTestDatabase();
        assertEquals(5, WritingList.getWritingSize());
        writings.removeID(20);
        assertEquals(0, writings.getWritingSize());
    }

    @Test
    public void clearItems_clearThirdWriting() {
        WritingList.clearAll(writings);
        initializeTestDatabase();
        String userInput1 = "clear type\\writing item\\3";
        try {
            ClearLoader.clearItems(userInput1, writings);
        } catch (WrongClearCommandFormat e) {
            System.out.println("This is not appropriate");
        }
        assertEquals("writing", ClearLoader.getType("type\\writing"));
        assertEquals("3", ClearLoader.getItem("item\\3"));
        assertEquals(4, WritingList.getWritingSize());
    }

    @Test
    public void clearAnItem_InvalidClearCommand_WrongClearCommandFormatThrown() {
        String userInput2 = "clear type\\";
        assertThrows(WrongClearCommandFormat.class, () -> ClearLoader.clearItems(userInput2, writings));
    }
}
