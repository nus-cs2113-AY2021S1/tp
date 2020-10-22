package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.financeit.manualtracker.TestCommands.generateCreateLedgerCorrectCommand;
import static seedu.financeit.manualtracker.TestCommands.generateCreateLedgerErrorCommand;
import static seedu.financeit.manualtracker.TestCommands.generateDeleteLedgerByDateCorrectCommand;
import static seedu.financeit.manualtracker.TestCommands.generateDeleteLedgerByDateErrorCommand;
import static seedu.financeit.manualtracker.TestCommands.generateDeleteLedgerByIdCorrectCommand;
import static seedu.financeit.manualtracker.TestCommands.generateDeleteLedgerByIdErrorCommand;

public class EntryTrackerTest {
    private static final int FREQUENCY_ERROR_ENTRY = 3;
    private static final int NUM_ENTRIES = 20;

    //@Test
    public void testManualTrackerByList() {
        CommandPacket testPacket;
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            if (i % FREQUENCY_ERROR_ENTRY == 0) {
                testPacket = generateCreateLedgerErrorCommand();
            } else {
                testPacket = generateCreateLedgerCorrectCommand(i);
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleCreateEntry();
        }
        int correctListNum = NUM_ENTRIES - (int)Math.floor((double)NUM_ENTRIES / FREQUENCY_ERROR_ENTRY);
        assertEquals(correctListNum, EntryTracker.entryList.getItemsSize());
        EntryTracker.entryList.removeAllItems();
    }

    //@Test
    public void testManualTrackerByDelete() {
        CommandPacket testPacket;
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            if (i % FREQUENCY_ERROR_ENTRY == 0) {
                testPacket = generateCreateLedgerErrorCommand();
            } else {
                testPacket = generateCreateLedgerCorrectCommand(i);
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleCreateEntry();
        }
        int correctListNum = NUM_ENTRIES - (int)Math.floor((double)NUM_ENTRIES / FREQUENCY_ERROR_ENTRY);

        for (int i = 1; i <= 4; i++) {
            switch (i) {
            case 1:
                testPacket = generateDeleteLedgerByDateCorrectCommand();
                break;
            case 2:
                testPacket = generateDeleteLedgerByIdCorrectCommand();
                break;
            case 3:
                testPacket = generateDeleteLedgerByIdErrorCommand();
                break;
            default:
                testPacket = generateDeleteLedgerByDateErrorCommand();
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleDeleteEntry();
        }
        assertEquals(correctListNum - 2, EntryTracker.entryList.getItemsSize());
    }

    //@Test
    public void testManualTrackerByDuplicateLedgers() {
        CommandPacket testPacket;
        for (int i = 0; i < NUM_ENTRIES; i++) {
            if (i % 2 == 0) {
                testPacket = generateCreateLedgerCorrectCommand(4);
            } else {
                testPacket = generateCreateLedgerCorrectCommand(3);
            }
            EntryTracker.setTestPacket(testPacket);
            EntryTracker.handleCreateEntry();
        }
        assertEquals(2, EntryTracker.entryList.getItemsSize());
        EntryTracker.entryList.removeAllItems();
    }
}
