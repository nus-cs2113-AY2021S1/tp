package seedu.duke.writinglist;

import org.junit.jupiter.api.Test;
import seedu.duke.database.ClearLoader;
import seedu.duke.writing.WritingList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WritingListTest {
    private static WritingList writings;

    private static void initializeTestDatabase() {
        LocalDate date = LocalDate.parse("28/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        WritingList.addPoem("fantasy", 1, "thih", "sdfa", "jdkfa", date);
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addPoem("fantasy", 20, "thih", "sdfa", "jdkfa", date);
        WritingList.addEssay("fantasy", 23, "thih", "sdfa", "jdkfa", date);
        WritingList.addEssay("fantasy", 13, "thih", "sdfa", "jdkfa", date);
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

