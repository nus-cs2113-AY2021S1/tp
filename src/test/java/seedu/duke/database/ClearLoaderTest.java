package seedu.duke.database;

import org.junit.jupiter.api.Test;
import seedu.duke.writing.WritingList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClearLoaderTest {
    private static WritingList writing;

    private static void initializeTestDatabase() {
        LocalDate date = LocalDate.parse("28/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyyy"));
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa", date);
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
