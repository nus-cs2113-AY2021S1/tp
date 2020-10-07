package seedu.financeit.manualtracker;

import seedu.financeit.manualtracker.subroutine.EntryTracker;
import seedu.financeit.utils.*;

import java.time.LocalDateTime;

public class ManualTracker {
    private static Ledger currLedger;
    private static LedgerList ledgerList = new LedgerList();
    private static CommandPacket packet;

    public static void main() {
        boolean endTracker = false;
        FiniteStateMachine fsm = new FiniteStateMachine(FiniteStateMachine.State.MAIN_MENU);
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
                System.out.println("Exiting Manual Tracker...");
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



    private static FiniteStateMachine.State handleCreateLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        for (String param : packet.getParamTypes()) {
            switch (param) {
            case "/date":
                Ledger ledger = new Ledger(packet.getParam(param));
                ledgerList.addLedger(ledger);
                System.out.println("Ledger created! " + ledger.getDate());
                break;
            default:
                System.out.println("Command failed.");
                break;
            }
        }
        return state;
    }

    private static FiniteStateMachine.State handleDeleteLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        for (String param : packet.getParamTypes()) {
            switch (param) {
            case "/date":
                String rawDate = packet.getParam(param);
                LocalDateTime dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawDate, "date"));
                ledgerList.removeLedger(dateTime);
                break;
            default:
                System.out.println("Command failed.");
                break;
            }
        }
        return state;
    }



    private static FiniteStateMachine.State handleShowLedger() {
        System.out.println("Show ledger");
        ledgerList.printList();
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwu");
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static void printCommandList(){
        Printer.setTitle("List of Commands");
        Printer.addRow("No.;Command            ;Input Format                  ");
        Printer.addRow("1.;Open ledger;ledger open /date <YYMMDD>");
        Printer.addRow("2.;New ledger;ledger new /date <YYMMDD>");
        Printer.addRow("3.;list ledgers;ledger list");
        Printer.addRow("4.;delete ledgers;ledger delete /date <YYMMDD>");
        Printer.addRow("5.;exit to main menu;exit");
        Printer.printList();
    }

    private static FiniteStateMachine.State handleMainMenu() {
        System.out.printf("You are now in Manual tracker!\n");
        System.out.println("Enter command!");
        System.out.println("Input \"commands\" for list of commands.");

        packet = UiManager.handleInput();
        UiManager.refreshPage();
        //System.out.println(packet);
        //System.out.println(packet.getCommandString());
        switch (packet.getCommandString()) {
        case "ledger open":
            //System.out.println("done");
            return FiniteStateMachine.State.OPEN_LEDGER;
        case "ledger new":
            return FiniteStateMachine.State.CREATE_LEDGER;
        case "ledger list":
            return FiniteStateMachine.State.SHOW_LEDGER;
        case "ledger delete":
            return FiniteStateMachine.State.DELETE_LEDGER;
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

/*    private static FiniteStateMachine.State handleOpenLedger() {
        packet = UiManager.handleInput();
        System.out.println(packet);
        switch (packet.getCommandString()) {
        case "b":
            return FiniteStateMachine.State.MAIN_MENU;
        default:
            System.out.println("Command not recognised. Try again.");
            return FiniteStateMachine.State.OPEN_LEDGER;
        }
    }*/

    private static FiniteStateMachine.State handleOpenLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        boolean endRoutine = false;
        for (String param : packet.getParamTypes()) {
            switch (param) {
            case "/date":
                String rawDate = packet.getParam(param);
                LocalDateTime dateTime = LocalDateTime.parse(InputParser.parseRawDateTime(rawDate, "date"));
                currLedger = ledgerList.getLedgerFromDate(dateTime);
                break;
            default:
                System.out.println("Command failed.");
                return state;
            }
        }

        EntryTracker.setCurrLedger(currLedger);
        return EntryTracker.main();
    }
}
