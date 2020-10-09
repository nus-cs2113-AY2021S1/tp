package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.ObjectNotFoundException;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.FiniteStateMachine;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class EntryTracker {
    private static Ledger currLedger;
    private static EntryList entryList = new EntryList();
    private static CommandPacket packet;

    public static void setCurrLedger(Ledger ledger) {
        currLedger = ledger;
    }

    public static FiniteStateMachine.State main() {
        boolean endTracker = false;
        FiniteStateMachine fsm = new FiniteStateMachine(FiniteStateMachine.State.MAIN_MENU);
        while (!endTracker) {
            switch (fsm.getCurrState()) {
            case MAIN_MENU:
                fsm.setNextState(handleMainMenu());
                break;
            case CREATE_ENTRY:
                fsm.setNextState(handleCreateEntry());
                break;
            case EDIT_ENTRY:
                fsm.setNextState(handleCreateEntry("edit"));
                break;
            case DELETE_ENTRY:
                fsm.setNextState(handleDeleteEntry());
                break;
            case SHOW_ENTRY:
                fsm.setNextState(handleShowEntry());
                break;
            case INVALID_STATE:
                fsm.setNextState(handleInvalidState());
                break;
            case EXIT:
                System.out.println("Exiting subroutine...");
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
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static Entry createEntry(String paramType) throws AssertionError{
        Ledger ledger = null;

        switch (paramType) {
        case "/date":
            String rawTime = packet.getParam(paramType);
            try {
                LocalDateTime dateTime = InputParser.parseRawDateTime(rawTime, "time");
                entryList.checkDuplicates(dateTime);
                 = new Ledger(dateTime);
            } catch (DuplicateInputException exception) {
                throw new AssertionError();
            }
        }
        return ledger;
    }

    private static Entry getEntryFromList(String paramType) throws AssertionError{
        Entry entry = null;
        switch(paramType) {
        case "/date":
            String rawDate = packet.getParam(paramType);
            try {
                LocalDateTime dateTime = InputParser.parseRawDateTime(rawDate, "date");
                entry = entryList.getEntryFromDateTime(dateTime);
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
            } catch (ObjectNotFoundException exception) {
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
                entry = entryList.getEntryByIndex(index);
            } catch (IndexOutOfBoundsException exception) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                        "Index input is out of bounds!",
                        String.format("The range is from 1 to %d", entryList.getEntriesSize()));
            }
            break;
        }
        return entry;
    }

    private static FiniteStateMachine.State handleCreateEntry(String mode) {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        for (String paramType : packet.getParamTypes()) {
            switch (paramType) {
            case "/date":
                // Fall through
            case "/id":
                try {
                    Entry entry = getEntryFromList(paramType);
                    entryList.addEntry(entry);
                } catch (AssertionError Error) {
                    break;
                }
                break;
            default:
                System.out.println("Command failed.");
                break;
            }
        }
        return state;
    }

    private static FiniteStateMachine.State handleDeleteEntry() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        for (String paramType : packet.getParamTypes()) {
            switch (paramType) {
            case "/date":
                // Fall through
            case "/id":
                try {
                    Entry entry = getEntryFromList(paramType);
                    entryList.removeItem(entry);
                } catch (AssertionError Error) {
                    break;
                }
                break;
            default:
                System.out.println("Command failed.");
                break;
            }
        }
        return state;
    }

    private static FiniteStateMachine.State handleShowEntry() {
        System.out.println("Show entry");
        entryList.printList(currLedger.toString());
        return FiniteStateMachine.State.MAIN_MENU;
    }

    public static Constants.EntryType getEntryType() {
        for (String paramType : packet.getParamTypes()) {
            switch (paramType) {
            case "-i":
                return Constants.EntryType.INC;
            case "-e":
                return Constants.EntryType.EXP;
            default:
                System.out.println("Command failed.");
                break;
            }
        }
        return null;
    }

    private static FiniteStateMachine.State handleCreateEntry() {
        return handleCreateEntry(" ");
    }

    private static FiniteStateMachine.State handleCreateEntry(String mode) {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry entry;
        Double amount;
        System.out.println(packet);
        if (mode.equals("edit")) {
            int index = Integer.parseInt(packet.getParam("/id"));
            index = index - 1;
            entry = entryList.getEntryFromIndex(index);
        } else {
            entry = new Entry();
        }

        for (String paramType : packet.getParamTypes()) {
            switch (paramType) {
            case "/amt":
                amount = Double.parseDouble(packet.getParam(paramType));
                entry.setAmount(amount);
                break;
            case "-i":
                entry.setEntryType(Constants.EntryType.INC);
                break;
            case "-e":
                entry.setEntryType(Constants.EntryType.EXP);
                break;
            case "/time":
                entry.setDateTime(packet.getParam(paramType));
                break;
            case "/desc":
                entry.setDescription(packet.getParam(paramType));
                break;
            case "/cat":
                entry.setCategory(packet.getParam(paramType));
                break;
            case "-auto":
                entry.setIsAuto(true);
            default:
                System.out.println("Command failed.");
                break;
            }
        }

        if (mode != "edit") {
            entryList.addEntry(entry);
        }
        return state;
    }

    private static void printCommandList(){
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command                 ;Input Format                                                 ");
        TablePrinter.addRow("1.;New entry;entry new /time <HHMM> /info <string> /cat <category> -[i/e]");
        TablePrinter.addRow("2.;Edit entry;entry edit <parameter to edit>");
        TablePrinter.addRow("3.;list entries;entry list");
        TablePrinter.addRow("4.;delete entry;entry delete /time <HHMM>");
        TablePrinter.addRow("5.;exit to manual tracker;exit");
        TablePrinter.printList();
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwuuuuuuu");
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleMainMenu() {
        UiManager.printSpace();
        UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY, String.format("[ MAIN_MENU -> MANUAL_TRACKER_MENU -> ENTRY_TRACKER (LEDGER %s)", currLedger));
        System.out.printf("You are now in entry tracker for ledger [%s]!\n", currLedger);
        System.out.println("Enter command!");
        System.out.println("Input \"commands\" for list of commands.");

        packet = UiManager.handleInput();
        UiManager.refreshPage();
        switch (packet.getCommandString()) {
        case "entry edit":
            return FiniteStateMachine.State.EDIT_ENTRY;
        case "entry new":
            return FiniteStateMachine.State.CREATE_ENTRY;
        case "entry list":
            return FiniteStateMachine.State.SHOW_ENTRY;
        case "entry delete":
            return FiniteStateMachine.State.DELETE_ENTRY;
        case "exit":
            return FiniteStateMachine.State.EXIT;
        case "commands":
            printCommandList();
            return FiniteStateMachine.State.MAIN_MENU;
        default:
            System.out.println("Command not recognised. Try again.");
            return FiniteStateMachine.State.MAIN_MENU;
        }
    }
}
