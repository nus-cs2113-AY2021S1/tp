package seedu.financeit.manualtracker;

import seedu.financeit.utils.CommandPacket;
import seedu.financeit.utils.Constants;
import seedu.financeit.utils.FiniteStateMachine;
import seedu.financeit.utils.UiManager;

import java.util.ArrayList;


public class ManualTracker {
    private static ArrayList<Ledger> ledgers = new ArrayList<>();
    private static Ledger currLedger;

    private static CommandPacket packet;


    public static void main() {
        boolean endTracker = false;
        FiniteStateMachine FSM = new FiniteStateMachine(FiniteStateMachine.State.MAIN_MENU);
        while (!endTracker) {
            switch (FSM.getCurrState()) {
            case MAIN_MENU:
                FSM.setNextState(handleMainMenu());
                break;
            case CREATE_LEDGER:
                FSM.setNextState(handleCreateLedger());
                break;
            case OPEN_LEDGER:
                FSM.setNextState(handleOpenLedger());
                break;
            case DELETE_LEDGER:
                FSM.setNextState(handleDeleteLedger());
                break;
            case SHOW_LEDGER:
                FSM.setNextState(handleShowLedger());
                break;
            case CREATE_ENTRY:
                FSM.setNextState(handleAddEntry());
                break;
            case DELETE_ENTRY:
                FSM.setNextState(handleDeleteEntry());
                break;
            case SHOW_ENTRY:
                FSM.setNextState(handleShowEntry());
                break;
            case INVALID_STATE:
                FSM.setNextState(handleInvalidState());
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
            FSM.transitionState();
        }
    }

    private static FiniteStateMachine.State handleDeleteEntry() {
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleCreateLedger() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        System.out.println("create ledger");
        for(String param : packet.getParamTypes()) {
            System.out.println(param);
            switch(param){
            case "/date":
                Ledger ledger = new Ledger(packet.getParam(param));
                ledgers.add(ledger);
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
        for(String param : packet.getParamTypes()) {
            switch(param){
            case "/date":
                ledgers.remove(new Ledger(packet.getParam(param)));
                state = FiniteStateMachine.State.MAIN_MENU;
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
        return FiniteStateMachine.State.MAIN_MENU;
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwu");
        return FiniteStateMachine.State.MAIN_MENU;
    }

    public static Constants.EntryType getEntryType(){
        for(String param : packet.getParamTypes()) {
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
        for(String param : packet.getParamTypes()) {
            switch(param){
            case "-i":
                entry.setEntryType(Constants.EntryType.INC);
                break;
            case "-e":
                entry.setEntryType(Constants.EntryType.EXP);
                break;
            case "/time":
                entry.setTime(packet.getParam(param));
                break;
            case "/info":
                entry.setDescription(packet.getParam(param));
                break;
            case "/cat":
                entry.setCategory(packet.getParam(param), getEntryType());
                break;
            default:
                System.out.println("Command failed.");
                break;
            }
        }
        return state;
    }



    private static FiniteStateMachine.State handleMainMenu(){
        System.out.println("Enter something");
        String[][] input = {
                {"No." , "Command"},
                {"1.", "Open ledger"},
                {"2.", "New ledger"},
                {"3.", "list ledgers"},
                {"4", "delete ledgers"}
        };
        UiManager.printList(input);
        packet = UiManager.handleInput();
        System.out.println(packet);
        switch(packet.getCommandString()){
        case "open":
            System.out.println("done");
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

    private static FiniteStateMachine.State handleOpenLedger(){
        packet = UiManager.handleInput();
        System.out.println(packet);
        switch(packet.getCommandString()){
        case "b":
            return FiniteStateMachine.State.MAIN_MENU;
        default:
            System.out.println("Command not recognised. Try again.");
            return FiniteStateMachine.State.OPEN_LEDGER;
        }
    }
}
