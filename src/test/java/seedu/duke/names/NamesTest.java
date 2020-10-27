package seedu.duke.names;

import org.junit.jupiter.api.Test;
import seedu.duke.database.NamesDB;
import seedu.duke.exceptions.NameException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NamesTest {

    @Test
    void addName() throws NameException {
        NamesDB.loadDB(Names.nameList);
        int currentSize = Names.nameList.size();
        Names.addName("add name JUnit test for name");
        int sizeAfterAdding = Names.nameList.size();
        assertEquals(currentSize + 1, sizeAfterAdding);
    }

    @Test
    void deleteName() throws NameException {
        NamesDB.loadDB(Names.nameList);
        int currentSize = Names.nameList.size();
        Names.deleteName("delete name " + Names.nameList.size());
        int sizeAfterDeleting = Names.nameList.size();
        assertEquals(currentSize - 1, sizeAfterDeleting);
    }
}
