package seedu.financeit.manualtracker;

import seedu.financeit.utils.*;

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
            case CREATE_ENTRY:
                fsm.setNextState(handleAddEntry());
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

    private static FiniteStateMachine.State handleDeleteEntry() {
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleCreateLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        System.out.println("create ledger");
        for (String param : packet.getParamTypes()) {
            System.out.println(param);
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
        FiniteStateMachine.State state = FiniteStateMachine.State.CREATE_LEDGER;
        for (String param : packet.getParamTypes()) {
            switch (param) {
            case "/date":
                ledgerList.removeLedger(new Ledger(packet.getParam(param)));
                state = FiniteStateMachine.State.MAIN_MENU;
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
        return FiniteStateMachine.State.MAIN_MENU;
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

    private static FiniteStateMachine.State handleAddEntry() {
        FiniteStateMachine.State state = FiniteStateMachine.State.CREATE_LEDGER;
        Entry entry = new Entry();
        for (String paramType : packet.getParamTypes()) {
            switch (paramType) {
            case "-i":
                entry.setEntryType(Constants.EntryType.INC);
                break;
            case "-e":
                entry.setEntryType(Constants.EntryType.EXP);
                break;
            case "/time":
                entry.setTime(packet.getParam(paramType));
                break;
            case "/info":
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
        return state;
    }

    private static FiniteStateMachine.State handleMainMenu() {
        System.out.println("Enter something");
        Printer.setTitle("List of Commands");
        Printer.addRow("No.;Command;Input Format                  ");
        Printer.addRow("1.;Open ledger;open /date <YYMMDD>");
        Printer.addRow("2.;New ledger;new /date <YYMMDD>");
        Printer.addRow("3.;list ledgers;list");
        Printer.addRow("4.;delete ledgers;delete /date <YYMMDD>");
        Printer.printList();

        packet = UiManager.handleInput();
        //System.out.println(packet);
        //System.out.println(packet.getCommandString());
        switch (packet.getCommandString()) {
        case "open":
            //System.out.println("done");
            return FiniteStateMachine.State.OPEN_LEDGER;
        case "new":
            return FiniteStateMachine.State.CREATE_LEDGER;
        case "list":
            return FiniteStateMachine.State.SHOW_LEDGER;
        case "delete":
            return FiniteStateMachine.State.DELETE_LEDGER;
        case "exit":
            return FiniteStateMachine.State.EXIT;
        default:
            System.out.println("Command not recognised. Try again.");
            return FiniteStateMachine.State.MAIN_MENU;
        }
    }

    private static FiniteStateMachine.State handleOpenLedger() {
        packet = UiManager.handleInput();
        System.out.println(packet);
        switch (packet.getCommandString()) {
        case "b":
            return FiniteStateMachine.State.MAIN_MENU;
        default:
            System.out.println("Command not recognised. Try again.");
            return FiniteStateMachine.State.OPEN_LEDGER;
        }
    }
}
