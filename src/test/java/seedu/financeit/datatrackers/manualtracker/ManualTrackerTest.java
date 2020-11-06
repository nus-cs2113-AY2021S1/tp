package seedu.financeit.datatrackers.manualtracker;

import org.junit.jupiter.api.Test;
import seedu.financeit.common.CommandPacket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateCreateLedgerCorrectCommand;
import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateCreateLedgerErrorCommand;
import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateDeleteLedgerByDateCorrectCommand;
import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateDeleteLedgerByDateErrorCommand;
import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateDeleteLedgerByIdCorrectCommand;
import static seedu.financeit.datatrackers.manualtracker.TestCommands.generateDeleteLedgerByIdErrorCommand;

public class ManualTrackerTest {
    private static final int FREQUENCY_ERROR_ENTRY = 3;
    private static final int NUM_ENTRIES = 20;

    @Test
    public void testManualTrackerByList() {
        CommandPacket testPacket;
        int correctListNum = 0;
        ManualTracker.ledgerList.removeAllItems();
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            if (i % FREQUENCY_ERROR_ENTRY == 0) {
                testPacket = generateCreateLedgerErrorCommand();
                System.out.print("error!: ");
            } else {
                testPacket = generateCreateLedgerCorrectCommand(i);
                correctListNum++;
            }
            System.out.println(testPacket);

            ManualTracker.setTestPacket(testPacket);
            ManualTracker.handleCreateLedger();
        }
        System.out.println(ManualTracker.ledgerList.getItemsSize());
        assertEquals(correctListNum, ManualTracker.ledgerList.getItemsSize());
        ManualTracker.ledgerList.removeAllItems();
    }

    @Test
    public void testManualTrackerByDelete() {
        CommandPacket testPacket;
        ManualTracker.ledgerList.removeAllItems();
        for (int i = 1; i <= NUM_ENTRIES; i++) {
            testPacket = generateCreateLedgerCorrectCommand(i);
            ManualTracker.setTestPacket(testPacket);
            ManualTracker.handleCreateLedger();
        }
        int correctListNum = NUM_ENTRIES;

        for (int i = 1; i <= 4; i++) {
            switch (i) {
            case 1:
                testPacket = generateDeleteLedgerByDateCorrectCommand();
                correctListNum--;
                break;
            case 2:
                testPacket = generateDeleteLedgerByIdCorrectCommand();
                correctListNum--;
                break;
            case 3:
                testPacket = generateDeleteLedgerByIdErrorCommand();
                System.out.print("error!: ");
                break;
            default:
                testPacket = generateDeleteLedgerByDateErrorCommand();
                System.out.print("error!: ");
            }
            System.out.println(testPacket);

            ManualTracker.setTestPacket(testPacket);
            ManualTracker.handleDeleteLedger();
        }
        System.out.println(ManualTracker.ledgerList.getItemsSize());
        assertEquals(correctListNum, ManualTracker.ledgerList.getItemsSize());
        ManualTracker.ledgerList.removeAllItems();
    }

    @Test
    public void testManualTrackerByDuplicateLedgers() {
        CommandPacket testPacket;
        ManualTracker.ledgerList.removeAllItems();
        for (int i = 0; i < NUM_ENTRIES; i++) {
            if (i % 2 == 0) {
                testPacket = generateCreateLedgerCorrectCommand(4);
            } else {
                testPacket = generateCreateLedgerCorrectCommand(3);
            }
            ManualTracker.setTestPacket(testPacket);
            ManualTracker.handleCreateLedger();
            System.out.println(testPacket);
        }

        assertEquals(2, ManualTracker.ledgerList.getItemsSize());
        ManualTracker.ledgerList.removeAllItems();
    }
}
