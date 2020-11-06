package seedu.financeit.datatrackers.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.datatrackers.entrytracker.EntryTracker;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.manualtracker.ledgerhandlers.CreateLedgerHandler;
import seedu.financeit.datatrackers.manualtracker.ledgerhandlers.RetrieveLedgerHandler;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

/**
 * Class to handle routine for manual ledger management.
 */
public class ManualTracker {
    static LedgerList ledgerList = new LedgerList();
    private static CommandPacket packet;
    private static boolean isUnderTest = false;
    private static boolean endTracker;
    GoalTracker goalTracker = new GoalTracker();

    public static void setTestPacket(CommandPacket inputPacket) {
        packet = inputPacket;
        isUnderTest = true;
    }

    public static void execute() {
        endTracker = false;
        UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Welcome to Manual Tracker!");
        while (!endTracker) {
            handleMainMenu();
        }
    }

    public static LedgerList getLedgerList() {
        return ledgerList;
    }

    public static void setCommandPacket(CommandPacket p) {
        packet = p;
    }

    public static void createLedger() {
        handleCreateLedger();
    }

    private static void handleMainMenu() {
        UiManager.printWithStatusIcon(Common.PrintType.DIRECTORY, "[ MAIN_MENU -> MANUAL_TRACKER_MENU ]");
        UiManager.printInputPromptMessage();
        String input;

        if (!isUnderTest) {
            input = UiManager.handleInput();
            packet = InputParser.getInstance().parseInput(input);
        }

        UiManager.refreshPage();
        switch (packet.getCommandString()) {
        case "open":
            handleOpenLedger();
            break;
        case "new":
            handleCreateLedger();
            break;
        case "list":
            handleShowLedger();
            break;
        case "delete":
            handleDeleteLedger();
            break;
        case "exit":
            endTracker = true;
            break;
        case "commands":
            printCommandList();
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Command failed. Try again.");
            break;
        }
    }

    static void handleCreateLedger() {
        CreateLedgerHandler createLedgerHandler = CreateLedgerHandler.getInstance();

        Ledger ledger;
        try {
            // CreateLedgerCommand instance generates a new Ledger instance
            // from the params specified in the command.
            createLedgerHandler.handlePacket(packet);
            ledger = createLedgerHandler.getCurrLedger();

            // Checking of duplicates
            if (ledgerList.isItemDuplicate(ledger)) {
                throw new DuplicateInputException();
            }

            // Append ledger to list.
            ledgerList.addItem(ledger);

            GoalTracker.storeLedgerDate(ledger);

            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                String.format("%s created!", ledger.getName()));
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                exception.getMessage());
        } catch (DuplicateInputException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                "Duplicate item already exists in the list; not added!");
        } finally {
            if (!createLedgerHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
    }

    static void handleDeleteLedger() {
        Ledger deletedLedger;
        RetrieveLedgerHandler retrieveLedgerHandler = RetrieveLedgerHandler.getInstance();
        try {
            // RetrieveLedgerCommand instance retrieves the corresponding ledger instance
            // from the ledgerList instance.
            retrieveLedgerHandler.handlePacket(packet, ledgerList);
            deletedLedger = (Ledger) ledgerList.getItemAtCurrIndex();

            // Deletion of ledger.
            ledgerList.removeItemAtCurrIndex();
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                String.format("%s deleted!", deletedLedger.getName()));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                exception.getMessage());
        } finally {
            if (!retrieveLedgerHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
    }

    private static void handleShowLedger() {
        ledgerList.printList();
    }

    private static void handleOpenLedger() {
        Ledger ledger;
        RetrieveLedgerHandler retrieveLedgerHandler = RetrieveLedgerHandler.getInstance();
        try {
            // RetrieveLedgerCommand instance retrieves the corresponding ledger instance
            // from the ledgerList instance.
            retrieveLedgerHandler.handlePacket(packet, ledgerList);
            ledger = (Ledger) ledgerList.getItemAtCurrIndex();

            // Set EntryTracker to access entries stored under this particular ledger instance.
            EntryTracker.setCurrLedger(ledger);

            GoalTracker.storeLedgerDate(ledger);

            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                String.format("%s opened!", ledger.getName()));

            EntryTracker.execute();
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                exception.getMessage());
            return;
        } catch (ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                exception.getMessage());

            // If the ledger specified does not exist, a new ledger instance with the corresponding
            // date will be generated and "opened".
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                "Generating new ledger...");
            handleCreateLedger();
            handleOpenLedger();
        } finally {
            if (!retrieveLedgerHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
    }

    private static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command            ;Input Format                  ");
        TablePrinter.addRow("1.;Open ledger;open /date {YYMMDD}");
        TablePrinter.addRow("2.;New ledger;new /date {YYMMDD}");
        TablePrinter.addRow("3.;list ledgers;list");
        TablePrinter.addRow("4.;delete ledgers;delete /date {YYMMDD}");
        TablePrinter.addRow("5.;exit to main menu;exit");
        TablePrinter.printList();
    }
}
