package seedu.duke.writinglist;

import org.junit.jupiter.api.Test;
import seedu.duke.writing.WritingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WritingListTest {
    private static void initializeTestDatabase() {
        WritingList.addPoem("fantasy", "2020-12-20", "thih", "sdfa", "jdkfa", 12);
        WritingList.addPoem("fantasy", "2020-12-20", "thih", "sdfa", "jdkfa", 12);
        WritingList.addPoem("fantasy", "2020-12-20", "thih", "sdfa", "jdkfa", 12);
        WritingList.addEssay("fantasy", "2020-12-20", "thih", "sdfa", "jdkfa", 12);
        WritingList.addEssay("fantasy", "2020-12-20", "thih", "sdfa", "jdkfa", 12);
    }

    @Test
    public void removeAllWritings_getWritingSize() {
        //WritingList.clearAll();
        //initializeTestDatabase();
        //assertEquals(5, WritingList.getWritingSize());
    }
}

