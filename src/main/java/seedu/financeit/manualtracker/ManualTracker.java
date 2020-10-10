package seedu.financeit.manualtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.EmptyParamException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.manualtracker.subroutine.EntryTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.FiniteStateMachine;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class ManualTracker {
    private static Ledger currLedger;
    private static LedgerList ledgerList = new LedgerList();
    private static CommandPacket packet;

    public static void main() {
        boolean endTracker = false;
        FiniteStateMachine fsm = new FiniteStateMachine(FiniteStateMachine.State.MAIN_MENU);
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Manual Tracker!");
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
                UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Exiting from ManualTracker");
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

    private static FiniteStateMachine.State handleMainMenu() {
        UiManager.printSpace();
        UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY, "[ MAIN_MENU -> MANUAL_TRACKER_MENU ]");
        UiManager.printWithStatusIcon(Constants.PrintType.INSTRUCTION,
                "Enter a command! ",
                "Input \"commands\" for list of commands."
        );

        packet = UiManager.handleInput();
        // System.out.println(packet);
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
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Command failed. Try again.");
            return FiniteStateMachine.State.MAIN_MENU;
        }
    }

    private static FiniteStateMachine.State handleCreateLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Ledger ledger = null;
        try {
            ledger = new Ledger(packet);
            ledgerList.addItem(ledger);
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (DuplicateInputException | InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return state;
    }

    private static FiniteStateMachine.State handleDeleteLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Ledger ledger;
        try {
            ledgerList.setCurrItem(packet);
            ledger = (Ledger) ledgerList.getCurrItem();
            ledgerList.removeItem(ledger);
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input failed due to param error.");
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage());
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

        try {
            ledgerList.setCurrItem(packet);
            ledger = (Ledger) ledgerList.getCurrItem();
            EntryTracker.setCurrLedger(ledger);
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input failed due to param error.");
            return state;
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage());
            return state;
        }
        return EntryTracker.main();
    }

    private static Ledger createLedgerWithErrorHandling(CommandPacket packet) throws AssertionError {
        Ledger ledger = null;

        try {
            ledger = new Ledger(packet);
        } catch (DateTimeException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Not a valid date on the Gregorian Calendar!",
                    "Check your input again against the following format!",
                    "Date format: YYMMDD",
                    "Time format: HHMM");
            throw new AssertionError();
        } catch (InvalidParameterException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input format is not recognised.",
                    "Check your input again against the following format!",
                    "Date format: YYMMDD",
                    "Time format: HHMM");
            throw new AssertionError();
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
            throw new AssertionError();
        } catch (EmptyParamException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage(),
                    "Enter \"commands\" to check format!");
            throw new AssertionError();
        }
        return ledger;
    }

    private static Ledger getLedgerWithErrorHandling(String paramType) throws AssertionError {
        Ledger ledger = null;
        switch (paramType) {
        case "/date":
            String rawDate = packet.getParam(paramType);
            try {
                LocalDateTime dateTime = InputParser.parseRawDateTime(rawDate, "date");
                ledger = (Ledger) ledgerList.getItemFromDateTime(dateTime);
            } catch (NullPointerException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        "No params supplied to " + paramType,
                        "Enter \"commands\" to check format!");
                throw new AssertionError();
            } catch (DateTimeException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        "Not a valid date on the Gregorian Calendar!",
                        "Check your input again against the following format!",
                        "Date format: YYMMDD",
                        "Time format: HHMM");
                throw new AssertionError();
            } catch (InvalidParameterException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        "Input format is not recognised.",
                        "Check your input again against the following format!",
                        "Date format: YYMMDD",
                        "Time format: HHMM");
                throw new AssertionError();
            } catch (ItemNotFoundException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        String.format("Ledger of date %s does not exist!", rawDate));
                throw new AssertionError();
            }
            break;

        case "/id":
            int index = Integer.parseInt(packet.getParam(paramType));
            // To account for offset of array indexing where beginning index is 0
            index = index - 1;
            try {
                ledger = (Ledger) ledgerList.getLedgerByIndex(index);
            } catch (IndexOutOfBoundsException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        "Index input is out of bounds!",
                        String.format("The range is from 1 to %d", ledgerList.getItemsSize()));
            }
            break;

        default:
            // Fall through
            break;
        }
        return ledger;
    }

    private static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command            ;Input Format                  ");
        TablePrinter.addRow("1.;Open ledger;ledger open /date <YYMMDD>");
        TablePrinter.addRow("2.;New ledger;ledger new /date <YYMMDD>");
        TablePrinter.addRow("3.;list ledgers;ledger list");
        TablePrinter.addRow("4.;delete ledgers;ledger delete /date <YYMMDD>");
        TablePrinter.addRow("5.;exit to main menu;exit");
        TablePrinter.printList();
    }
}
