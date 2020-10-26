package cheatsheet;

import exception.CommandException;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CheatSheetListTest {
    private static Logger logger = Logger.getLogger("Tester");

    @Test
    void testGetSize() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        assertEquals(10, cheatSheetList.getSize());
        logger.log(Level.INFO, "Finished GetSize test");
    }

    @Test
    void testClear() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        cheatSheetList.clear();
        assertEquals(0, cheatSheetList.getSize());
        logger.log(Level.INFO, "Finished Clear test");
    }

    @Test
    void testAdd() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        assertEquals(10, cheatSheetList.getSize());
        logger.log(Level.INFO, "Finished Add test");
    }

    @Test
    void testRemove() {
        // remove(String name);
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }

        try {
            cheatSheetList.remove("Name1");
            cheatSheetList.remove("Name3");
            cheatSheetList.remove("Name5");
            assertEquals(7, cheatSheetList.getSize());
        } catch (CommandException e) {
            fail();
        }

        // remove(int index);
        cheatSheetList.clear();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }

        try {
            cheatSheetList.remove(1);
            cheatSheetList.remove(3);
            cheatSheetList.remove(5);
            assertEquals(7, cheatSheetList.getSize());
        } catch (CommandException e) {
            fail();
        }
        logger.log(Level.INFO, "Finished Remove test");

    }

    @Test
    void remove_negativeIndex_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            cheatSheetList.remove(-1);
            fail();
        } catch (CommandException e) {
            assertEquals("Please enter a valid index", e.getMessage());
        }
    }

    @Test
    void remove_nonExistentName_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            cheatSheetList.remove("dummy");
            fail();
        } catch (CommandException e) {
            assertEquals("Please enter a valid index", e.getMessage());
        }
    }

    @Test
    void testGetCheatSheet() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        CheatSheet test = new CheatSheet("Name1", "Language1", "Details1");
        for (int i = 0; i < 10; i++) {
            if (i == 1) {
                cheatSheetList.add(test);
            } else {
                cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
            }
        }

        // test to get cheatsheet by index and by name
        try {
            assertEquals(test, cheatSheetList.get(2));
            assertEquals(test, cheatSheetList.get("Name1"));
        } catch (CommandException e) {
            fail();
        }
        logger.log(Level.INFO, "Finished GetCheatSheet test");
    }

    @Test
    void getCheatSheet_negativeIndex_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        CheatSheet test;
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            test = cheatSheetList.get(-1);
            fail();
        } catch (CommandException e) {
            assertEquals("Please enter a valid index", e.getMessage());
        }
    }

    @Test
    void getCheatSheet_nonExistentName_exceptionThrown() {
        CheatSheetList cheatSheetList = new CheatSheetList();
        CheatSheet test;
        for (int i = 0; i < 10; i++) {
            cheatSheetList.add(new CheatSheet("Name" + i, "Language" + i, "Details" + i));
        }
        try {
            test = cheatSheetList.get("cheatlogs");
            fail();
        } catch (CommandException e) {
            assertEquals("Please enter a valid name", e.getMessage());
        }
    }
}
