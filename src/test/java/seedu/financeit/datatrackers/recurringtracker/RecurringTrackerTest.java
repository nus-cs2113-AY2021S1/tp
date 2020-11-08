package seedu.financeit.datatrackers.recurringtracker;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.datatrackers.recurringtracker.recurringhandlers.CreateEntryHandler;
import seedu.financeit.datatrackers.recurringtracker.recurringhandlers.EditEntryHandler;
import seedu.financeit.datatrackers.recurringtracker.recurringhandlers.RetrieveEntryHandler;
import seedu.financeit.testutil.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;


public class RecurringTrackerTest {
    CommandPacket testPacket;
    public static final int ENTRIES_TO_CREATE = 15;
    final String[] sampleCompulsoryParams = new String[] {"-i or -e", "/desc", "/amt", "day"};

    CreateEntryHandler createHandler = CreateEntryHandler.getInstance();
    EditEntryHandler editHandler = EditEntryHandler.getInstance();
    RetrieveEntryHandler retrieveHandler = RetrieveEntryHandler.getInstance();

    @Test
    public RecurringEntry createEntryHandler_validInput_validEntryCreated() {
        String[] validParamTypes = new String[] {"-e", "/desc", "/amt", "/day", "/notes"};
        String[] validParamArguments = new String[] {"", "Test23123//>?>_+_~#$#@",
            "3490.34", "15", "OIYH(*^(*ot9w3848(*&(*~~||///"};
        clearEntries();
        testPacket = TestUtil.createCommandPacket("new", validParamTypes, validParamArguments);
        try {
            createHandler.handlePacket(testPacket);
        } catch (Exception exception) {
            fail("Unexpected exception! " + exception.getMessage());
        }
        RecurringEntry entry = createHandler.getEntry();
        assertEquals(Common.EntryType.EXP, entry.entryType);
        assertEquals(validParamArguments[1], entry.description);
        assertEquals(validParamArguments[2], String.valueOf(entry.amount));
        assertEquals(validParamArguments[3], String.valueOf(entry.day));
        assertEquals(validParamArguments[4], entry.notes);
        assertFalse(entry.isAuto);

        return entry;
    }

    @Test
    public void editEntryHandler_oneParamToEdit_entryEdited() {
        String[] validEditParamTypes = new String[] {"-e", "/desc", "/amt", "/day", "/notes"};
        String[] validEditParamArguments = new String[] {"", "NewDescription)*(&)(908945",
            "1999.99", "27", "HP08709HN*^*D?:L[]``]/<>"};
        RecurringEntry entry = new RecurringEntry();
        clearEntries();
        testPacket = TestCommands.generateCreateCorrectEntryCommandAutoIncome();
        try {
            createHandler.handlePacket(testPacket);
            entry = createHandler.getEntry();
            editHandler.setEntry(entry);
            for (int i = 0; i < validEditParamTypes.length; i++) {
                testPacket = TestUtil.createCommandPacket("edit",
                        new String[]{"/id", validEditParamTypes[i]},
                        new String[]{"1", validEditParamArguments[i]});
                editHandler.handlePacket(testPacket);
            }
            entry = editHandler.getEntry();
        } catch (Exception exception) {
            fail("Unexpected exception! " + exception.getMessage());
        }
        assertEquals(Common.EntryType.EXP, entry.entryType);
        assertEquals(validEditParamArguments[1], entry.description);
        assertEquals(validEditParamArguments[2], String.valueOf(entry.amount));
        assertEquals(validEditParamArguments[3], String.valueOf(entry.day));
        assertEquals(validEditParamArguments[4], entry.notes);
        assertTrue(entry.isAuto);
    }

    @Test
    public void editEntryHandler_noParamToEdit_errorThrown() {
        clearEntries();
        EditEntryHandler editHandler = EditEntryHandler.getInstance();

        RecurringEntry entry = createEntryHandler_validInput_validEntryCreated();
        editHandler.setEntry(entry);
        testPacket = TestUtil.createCommandPacket("edit", new String[]{"/id"}, new String[]{"1"});
        try {
            editHandler.handlePacket(testPacket);
        } catch (Exception exception) {
            assertEquals("At least 1 param required for edit!", exception.getMessage());
        }
    }

    @Test
    public void retrieveEntryHandler_validIndex_entryRetrieved() {
        clearEntries();
        RecurringEntryList entries = new RecurringEntryList();
        RecurringEntry entry = createEntryHandler_validInput_validEntryCreated();
        entries.addItem(entry);
        testPacket = TestUtil.createCommandPacket("edit", new String[]{"/id"}, new String[]{"1"});
        try {
            retrieveHandler.handlePacket(testPacket, entries);
            entries.getItemAtCurrIndex();
        } catch (Exception exception) {
            assertEquals("At least 1 param required for edit!", exception.getMessage());
        }
    }

    @Test
    public void handleNewEntry_noMissingParams_entriesCreated() {
        clearEntries();
        for (int i = 0; i < ENTRIES_TO_CREATE; i++) {
            testPacket = TestCommands.generateCreateCorrectEntryCommandAutoIncome();
            RecurringTracker.handleNewEntry(testPacket);
        }
        assertEquals(ENTRIES_TO_CREATE, RecurringTracker.getEntries().getListSize());
    }


    @Test
    public void handleNewEntry_missingParams_entryNotCreated() {
        CommandPacket[] packets = TestCommands.generateCreateEntryMissingParamsCommands();
        //Do a for loop to vary which params are missing
        //Only loop 4 times, as there are 4 compulsory params
        for (int i = 0; i < 4; i++) {
            try {
                testPacket = packets[i];
                RecurringEntry entry = RecurringTracker.handleNewEntry(testPacket);
                assertNull(entry);
            } catch (Exception exception) {
                String expectedErrorMessage = "The following params require valid input: " + sampleCompulsoryParams[i];
                assertEquals(expectedErrorMessage, exception.getMessage());
            }
        }
    }

    @Test
    public void handleEditEntry_oneParamToEdit_entryEdited() {
        handleNewEntry_noMissingParams_entriesCreated();
        testPacket = TestUtil.createCommandPacket("edit",
                new String[]{"/id", "/desc"},
                new String[]{"5", "Edited description!!!!!!!"});
        RecurringTracker.handleEditEntry(testPacket);
        RecurringEntry entry = (RecurringEntry) RecurringTracker.getEntries().getItemAtIndex(4);
        assertEquals("Edited description!!!!!!!", entry.description);
    }

    public void clearEntries() {
        RecurringTracker.getEntries().removeAllItems();
    }

}
