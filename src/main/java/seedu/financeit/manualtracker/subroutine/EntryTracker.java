package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.utils.*;

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

    private static FiniteStateMachine.State handleDeleteEntry() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        System.out.println("Deleting ledger...");
        for (String param : packet.getParamTypes()) {
            System.out.println(param);
            switch (param) {
            case "/date":
                String rawDate = packet.getParam(param);
                LocalDateTime dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawDate, "date"));
                entryList.removeEntry(dateTime);

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
        entryList.printList();
        return FiniteStateMachine.State.MAIN_MENU;
    }

    public static Constants.EntryType getEntryType() {
        for (String param : packet.getParamTypes()) {
            switch (param) {
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
        if (mode.equals("edit")) {
            String rawTime = packet.getParam("/date");
            LocalDateTime dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawTime, "time"));
            entry = entryList.getEntryFromDateTime(dateTime);
            System.out.println("Entry created! " + entry.getTime());
        } else {
            entry = new Entry();
        }
        System.out.println(packet);
        for (String paramType : packet.getParamTypes()) {
            switch (paramType) {
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
                entry.setCategory(packet.getParam(paramType), getEntryType());
                break;
            default:
                System.out.println("Command failed.");
                break;
            }
        }

        if (mode != "edit") {
            entryList.addEntry(entry);
        }
        System.out.println(entry);
        return state;
    }

    private static void printCommandList(){
        Printer.setTitle("List of Commands");
        Printer.addRow("No.;Command                 ;Input Format                                                 ");
        Printer.addRow("1.;New entry;entry new /time <HHMM> /info <string> /cat <category> -[i/e]");
        Printer.addRow("2.;Edit entry;entry edit <parameter to edit>");
        Printer.addRow("3.;list entries;entry list");
        Printer.addRow("4.;delete entry;entry delete /time <HHMM>");
        Printer.addRow("5.;exit to manual tracker;exit");
        Printer.printList();
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwuuuuuuu");
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleMainMenu() {
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
