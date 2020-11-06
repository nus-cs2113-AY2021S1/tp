package seedu.financeit.datatrackers.entrytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.datatrackers.manualtracker.TypicalLedgerEntries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.financeit.datatrackers.entrytracker.TestCommands.generateCreateEntryCorrectCommand;
import static seedu.financeit.datatrackers.entrytracker.TestCommands.generateCreateEntryErrorCommand;
import static seedu.financeit.datatrackers.entrytracker.TestCommands.generateDeleteEntryByIdCorrectCommand;
import static seedu.financeit.datatrackers.entrytracker.TestCommands.generateDeleteEntryByIdErrorCommand;
import static seedu.financeit.datatrackers.entrytracker.TestCommands.generateEditEntryCorrectCommand;

public class EntryTrackerTest {
    private static final int FREQUENCY_ERROR_ENTRY = 3;
    private static final int NUM_ENTRIES = 50;

    /**
     * Function sets up the "opened" ledger whereby entry operations will be performed upon.
     */
    @BeforeEach
    void setup() {
        EntryTracker.setCurrLedger(TypicalLedgerEntries.getInstance().generateTypicalLedger1());
    }

    /**
     * Test handleCreateEntry that evaluates if valid entries are added into the list, and
     * invalid are not added into the list.
     */
    @Test
    public void testEntryTrackerByList() {
        CommandPacket testPacket;
        int count = 0;
        int numCorrectEntries = 0;
        // Illegal Params that will be generated with the Entry for negative test.
        String[] wrongParams = {
            "time",
            "cat",
            "amt"
        };
        String wrongParam;
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            if (i % FREQUENCY_ERROR_ENTRY == 0) {
                wrongParam = wrongParams[count % 3];
                testPacket = generateCreateEntryErrorCommand(wrongParam);
                count++;
            } else {
                testPacket = generateCreateEntryCorrectCommand(i);
                numCorrectEntries++;
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleCreateEntry(false);
        }
        assertEquals(numCorrectEntries, EntryTracker.entryList.getItemsSize());
        EntryTracker.entryList.removeAllItems();
    }

    @Test
    public void testEntryTrackerByDelete() {
        CommandPacket testPacket = null;
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            testPacket = generateCreateEntryCorrectCommand(i);
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleCreateEntry(false);
        }
        int actualListNum = EntryTracker.entryList.getItemsSize();
        for (int i = 1; i <= 20; i++) {
            switch (i % 4) {
            case 0:
                //Fall through
            case 1:
                testPacket = generateDeleteEntryByIdCorrectCommand();
                actualListNum -= 1;
                break;
            case 2:
                //Fall through
            case 3:
                testPacket = generateDeleteEntryByIdErrorCommand();
                break;
            default:
                // Fall- through
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleDeleteEntry();
        }
        int expectedListNum = EntryTracker.entryList.getItemsSize();
        assertEquals(actualListNum, expectedListNum);
    }

    @Test
    public void testEntryTrackerByEditEntries() {
        int commonSeed = 3;
        TypicalEntryEntries.getInstance().setSeed(commonSeed);
        final Entry expectedEntry = TypicalEntryEntries.getInstance().generateTypicalEntryFromSeed();
        // Create a fodder entry to overwrite using edit operation.
        TypicalEntryEntries.getInstance().generateTypicalEntry1();
        EntryTracker.setTestPacket(TypicalEntryEntries.getInstance().packet);
        EntryTracker.handleCreateEntry(false);

        // Create command packet to invoke edit operation.
        CommandPacket testPacket = generateEditEntryCorrectCommand(commonSeed);
        EntryTracker.setTestPacket(testPacket);
        EntryTracker.handleEditEntry();
        Entry actualEntry = (Entry) EntryTracker.entryList.getItemAtCurrIndex(0);

        assertEquals(actualEntry, expectedEntry);
        EntryTracker.entryList.removeAllItems();
    }

    @Test
    public void testEntryTrackerByDuplicateEntries() {
        CommandPacket testPacket;
        for (int i = 0; i < NUM_ENTRIES; i++) {
            if (i % 2 == 0) {
                testPacket = generateCreateEntryCorrectCommand(4);
            } else {
                testPacket = generateCreateEntryCorrectCommand(3);
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleCreateEntry(false);
        }
        assertEquals(2, EntryTracker.entryList.getItemsSize());
        EntryTracker.entryList.removeAllItems();
    }
}
