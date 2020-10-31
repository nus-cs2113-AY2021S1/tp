package seedu.duke.database;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FileEmptyException;
import seedu.duke.exceptions.ItemNotFoundedException;
import seedu.duke.exceptions.WrongClearCommandFormat;
import seedu.duke.wordlist.WordList;
import seedu.duke.words.Words;
import seedu.duke.writing.WritingList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.filters.WordsFilterTest.initializeTestDatabase;

public class ClearLoaderTest {
    private static WritingList writings;
    private static ArrayList<Words> wordList;

    private static void initializeTestDatabaseForWriting() {
        LocalDate date = LocalDate.parse("28/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addPoem("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa", date);
        WritingList.addEssay("fantasy", 12, "thih", "sdfa", "jdkfa", date);
    }

    @Test
    public void clearAll_getWritingSize() {
        WritingList.clearAll(writings);
        initializeTestDatabaseForWriting();
        assertEquals(5, WritingList.getWritingSize());
        WritingList.clearAll(writings);
        assertEquals(0, WritingList.getWritingSize());
    }

    @Test
    public void removeID_removeAll_noneOfWritingLeft() {
        WritingList.clearAll(writings);
        initializeTestDatabaseForWriting();
        assertEquals(5, WritingList.getWritingSize());
        try {
            writings.removeID(12);
        } catch (FileEmptyException e) {
            System.out.println("Empty file of Writing!");
        } catch (ItemNotFoundedException e) {
            System.out.println("Item not found in the writing archive");
        }
        assertEquals(0, writings.getWritingSize());
    }

    @Test
    public void clearItems_clearThirdWriting() {
        WritingList.clearAll(writings);
        initializeTestDatabaseForWriting();
        String userInput1 = "clear type\\writing item\\3";
        /*try {
            ClearLoader.clearItems(userInput1, writings, wordList);
        } catch (WrongClearCommandFormat e) {
            System.out.println("This is not appropriate");
        }*/
        assertEquals("writing", ClearLoader.getType("type\\writing"));
        assertEquals("3", ClearLoader.getItem("item\\3"));
        //assertEquals(4, WritingList.getWritingSize());
    }

    @Test
    public void clearAnItem_InvalidClearCommand_WrongClearCommandFormatThrown() {
        String userInput2 = "clear type\\";
        assertThrows(WrongClearCommandFormat.class, () -> ClearLoader.clearItems(userInput2, writings, wordList));
    }

    @Test
    public void clearByWronglyFormattedCommand_WrongCommandFormatThrown() {
        initializeTestDatabase();
        String userInput = "clear type\\word item\\-non dfad";
        assertThrows(WrongClearCommandFormat.class, () -> ClearLoader.clearItems(userInput, writings, wordList));
    }

    @Test
    public void initializeAnEmptyWritingList_FileEmptyExceptionThrown() {
        WritingList.clearAll(writings);
        String userInput = "clear type\\writing item\\0";
        assertThrows(NullPointerException.class, () -> ClearLoader.clearWritingWithID("0", writings));
    }
}
