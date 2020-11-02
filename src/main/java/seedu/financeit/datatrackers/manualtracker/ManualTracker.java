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
import seedu.financeit.utils.FiniteStateMachine;

/**
 * Class to handle routine for manual ledger management.
 */
public class ManualTracker {
    static LedgerList ledgerList = new LedgerList();
    private static GoalTracker goalTrack = new GoalTracker();
    private static CommandPacket packet;
    private static boolean isUnderTest = false;

    public static void setTestPacket(CommandPacket inputPacket) {
        packet = inputPacket;
        isUnderTest = true;
    }

    public static void main() {
        boolean endTracker = false;
        FiniteStateMachine fsm = new FiniteStateMachine(FiniteStateMachine.State.MAIN_MENU);
        UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Welcome to Manual Tracker!");
        while (!endTracker) {
            switch (fsm.getCurrState()) {
            case MAIN_MENU:
                fsm.setNextState(handleMainMenu());
                break;
            case CREATE_LEDGER:
                fsm.setNextState(handleCreateLedger());
                break;
            case OPEN_LEDGER:
                fsm.setNextState(handleOpenLedger());
                break;
            case DELETE_LEDGER:
                fsm.setNextState(handleDeleteLedger());
                break;
            case SHOW_LEDGER:
                fsm.setNextState(handleShowLedger());
                break;
            case INVALID_STATE:
                fsm.setNextState(handleInvalidState());
                break;
            case EXIT:
                UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Exiting from ManualTracker");
                endTracker = true;
                break;
            case END_TRACKER:
                endTracker = true;
                break;
            default:
                break;
            }
            fsm.transitionState();
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

    private static FiniteStateMachine.State handleMainMenu() {
        UiManager.printWithStatusIcon(Common.PrintType.DIRECTORY, "[ MAIN_MENU -> MANUAL_TRACKER_MENU ]");
        UiManager.printInputPromptMessage();
        String input;

        if (!isUnderTest) {
            input = UiManager.handleInput();
            packet = InputParser.getInstance().parseInput(input);
        }

        UiManager.refreshPage();
        switch (packet.getCommandString()) {
        case "ledger open":
            // Fall through
        case "open":
            return FiniteStateMachine.State.OPEN_LEDGER;
        case "ledger new":
            // Fall through
        case "new":
            return FiniteStateMachine.State.CREATE_LEDGER;
        case "ledger list":
            // Fall through
        case "list":
            return FiniteStateMachine.State.SHOW_LEDGER;
        case "ledger delete":
            // Fall through
        case "delete":
            return FiniteStateMachine.State.DELETE_LEDGER;
        case "exit":
            return FiniteStateMachine.State.EXIT;
        case "commands":
            printCommandList();
            return FiniteStateMachine.State.MAIN_MENU;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Command failed. Try again.");
            return FiniteStateMachine.State.MAIN_MENU;
        }
    }

    static FiniteStateMachine.State handleCreateLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
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
        return state;
    }

    static FiniteStateMachine.State handleDeleteLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
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
        return state;
    }

    private static FiniteStateMachine.State handleShowLedger() {
        ledgerList.printList();
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwu");
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleOpenLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
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
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                exception.getMessage());
            return state;
        } catch (ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                exception.getMessage());

            // If the ledger specified does not exist, a new ledger instance with the corresponding
            // date will be generated and "opened".
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                "Generating new ledger...");
            handleCreateLedger();
            return handleOpenLedger();
        } finally {
            if (!retrieveLedgerHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return EntryTracker.main();
    }

    private static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command            ;Input Format                  ");
        TablePrinter.addRow("1.;Open ledger;ledger open /date {YYMMDD}");
        TablePrinter.addRow("2.;New ledger;ledger new /date {YYMMDD}");
        TablePrinter.addRow("3.;list ledgers;ledger list");
        TablePrinter.addRow("4.;delete ledgers;ledger delete /date {YYMMDD}");
        TablePrinter.addRow("5.;exit to main menu;exit");
        TablePrinter.printList();
    }
}
