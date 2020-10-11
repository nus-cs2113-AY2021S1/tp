package seedu.financeit.manualtracker.subroutine;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.ConflictingItemReference;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.goaltracker.TotalGoal;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.FiniteStateMachine;

public class EntryTracker {
    private static Ledger currLedger;
    private static EntryList entryList;
    public static CommandPacket packet;
    private static TotalGoal totalGoal = new TotalGoal();

    public static void setCurrLedger(Ledger ledger) {
        currLedger = ledger;
        entryList = ledger.entryList;
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
                fsm.setNextState(handleEditEntry());
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


    public static EntryList getEntryList(){ return entryList; }

    private static FiniteStateMachine.State handleMainMenu() {
        UiManager.printSpace();
        UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY,
            String.format("[ MAIN_MENU -> MANUAL_TRACKER_MENU -> ENTRY_TRACKER (LEDGER %s)", currLedger));
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
            String.format("You are now in entry tracker for ledger [%s]!", currLedger),
                "Enter command!",
                "Input \"commands\" for list of commands."
        );

        String input = UiManager.handleInput();
        packet = new InputParser().parseInput(input);
        UiManager.refreshPage();
        switch (packet.getCommandString()) {
        case "entry edit":
            // Fall through
        case "edit":
            return FiniteStateMachine.State.EDIT_ENTRY;
        case "entry new":
            // Fall through
        case "new":
            return FiniteStateMachine.State.CREATE_ENTRY;
        case "entry list":
            // Fall through
        case "list":
            return FiniteStateMachine.State.SHOW_ENTRY;
        case "entry delete":
            // Fall through
        case "delete":
            return FiniteStateMachine.State.DELETE_ENTRY;
        case "exit":
            return FiniteStateMachine.State.EXIT;
        case "commands":
            printCommandList();
            return FiniteStateMachine.State.MAIN_MENU;
        case "cat":
            printValidCategories();
            return FiniteStateMachine.State.MAIN_MENU;
        default:
            System.out.println("Command not recognised. Try again.");
            return FiniteStateMachine.State.MAIN_MENU;
        }
    }

    private static FiniteStateMachine.State handleDeleteEntry() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry entry;
        try {
            entryList.setItemQueue(packet);
            entry = (Entry) entryList.getItemQueue();
            entryList.removeItem(entry);
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                String.format("%s deleted!", entry.getName()));
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input failed due to param error.");
        } catch (InsufficientParamsException | ItemNotFoundException | ConflictingItemReference exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage());
        }
        return state;
    }

    private static FiniteStateMachine.State handleShowEntry() {
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

    public static FiniteStateMachine.State handleCreateEntry() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry entry;

        try {
            entry = new Entry(packet);
            entryList.addItem(entry);
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                String.format("%s created!", entry.getName()));
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input failed due to param error.");
        } catch (DuplicateInputException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Entry specified already exists in the list!");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage());
        }
        return state;
    }

    private static FiniteStateMachine.State handleEditEntry() {
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry entry;

        try {
            entryList.setItemQueue(packet);
            entry = (Entry) entryList.getItemQueue();
            entry.handleParams(packet);
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                String.format("%s edited!", entry.getName()));
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Input failed due to param error.");
        } catch (InsufficientParamsException | ItemNotFoundException | ConflictingItemReference exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                exception.getMessage());
        }
        return state;
    }

    private static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command                 ;Input Format                                               ");
        TablePrinter.addRow("1.;New entry;entry new /time <HHMM> /info <string> /cat <category> -[i/e]");
        TablePrinter.addRow("2.;Edit entry;entry edit <parameter to edit>");
        TablePrinter.addRow("3.;list entries;entry list");
        TablePrinter.addRow("4.;delete entry;entry delete /time <HHMM>");
        TablePrinter.addRow("5.;exit to manual tracker;exit");
        TablePrinter.printList();
    }

    private static void printValidCategories() {
        TablePrinter.setTitle("List of Valid Categories");
        TablePrinter.addRow("Category;Input");
        for (String i : Constants.categoryMap.keySet()) {
            TablePrinter.addRow(String.format("%s;%s", i, Constants.categoryMap.get(i)));
        }
        TablePrinter.printList();
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwuuuuuuu");
        return FiniteStateMachine.State.MAIN_MENU;
    }


}
