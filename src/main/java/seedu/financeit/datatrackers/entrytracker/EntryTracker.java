package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.manualtracker.Ledger;
import seedu.financeit.datatrackers.entrytracker.commands.CreateEntryCommand;
import seedu.financeit.datatrackers.entrytracker.commands.EditEntryCommand;
import seedu.financeit.datatrackers.entrytracker.commands.RetrieveEntryCommand;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.FiniteStateMachine;

/**
 * Class to handle routine for manual entry management.
 */
public class EntryTracker {
    private static Ledger currLedger;
    static EntryList entryList;
    private static CommandPacket packet;
    private static boolean isUnderTest = false;

    public static void setTestPacket(CommandPacket inputPacket) {
        setCommandPacket(inputPacket);
        isUnderTest = true;
    }

    public static void setCommandPacket(CommandPacket p) {
        packet = p;
    }

    public static void createEntry() {
        handleCreateEntry();
    }

    private static GoalTracker goalTracker = new GoalTracker();

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
                // Fall through
                break;
            }
            fsm.transitionState();
        }
        return FiniteStateMachine.State.MAIN_MENU;
    }

    public static EntryList getEntryList() {
        return entryList;
    }

    private static FiniteStateMachine.State handleMainMenu() {
        String input;

        UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY,
                String.format("[ MAIN_MENU -> MANUAL_TRACKER_MENU -> ENTRY_TRACKER (LEDGER %s)", currLedger));
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                String.format("You are now in entry tracker for ledger [%s]!", currLedger),
                "Enter command!",
                "Input \"commands\" for list of commands."
        );

        if (!isUnderTest) {
            input = UiManager.handleInput();
            UiManager.refreshPage();
            packet = InputParser.getInstance().parseInput(input);
        }
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

    static FiniteStateMachine.State handleDeleteEntry() {
        RetrieveEntryCommand command = new RetrieveEntryCommand("/id");
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry deletedEntry;
        try {
            // RetrieveEntryCommand instance retrieves the corresponding entry instance
            // from the entryList instance.
            command.handlePacket(packet, entryList);
            deletedEntry = (Entry) entryList.getItemAtCurrIndex();

            // Deletion of entry.
            entryList.removeItemAtCurrIndex();

            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                    String.format("%s deleted!", deletedEntry.getName()));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!command.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return state;
    }

    static FiniteStateMachine.State handleShowEntry() {
        entryList.printList();
        return FiniteStateMachine.State.MAIN_MENU;
    }

    static FiniteStateMachine.State handleCreateEntry(Boolean... isPrintGoalInput) {
        boolean isPrintGoal = (isPrintGoalInput.length > 0 && isPrintGoalInput[0] == false)
            ? false
            : true;
        CreateEntryCommand command = new CreateEntryCommand(

        );
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry entry;
        try {
            // CreateLedgerCommand instance generates a new Ledger instance
            // from the params specified in the command.
            command.handlePacket(packet);
            entry = command.getCurrEntry();

            // Checking of duplicates
            if (entryList.isItemDuplicate(entry)) {
                throw new DuplicateInputException();
            }

            // Append entry to list.
            entryList.addItem(entry);

            // To allow disabling of print feature during JUnit testing.
            if (isPrintGoal == true) {
                goalTracker.targetGoalTracker(entry);
            }

            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                    String.format("%s created!", entry.getName()));
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } catch (DuplicateInputException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                "Duplicate item already exists in the list; not added!");
        } finally {
            if (!command.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return state;
    }

    static FiniteStateMachine.State handleEditEntry() {
        RetrieveEntryCommand retrieveEntryCommand = new RetrieveEntryCommand();
        EditEntryCommand editEntryCommand;
        FiniteStateMachine.State state = FiniteStateMachine.State.MAIN_MENU;
        Entry entry;
        try {
            // RetrieveEntryCommand instance retrieves the corresponding entry instance
            // from the entryList instance.
            retrieveEntryCommand.handlePacket(packet, entryList);
            entry = (Entry) entryList.getItemAtCurrIndex();

            // EditEntryCommand instance edits details of the corresponding ledger instance
            // from the entryList instance.
            editEntryCommand = new EditEntryCommand(entry);
            editEntryCommand.handlePacket(packet);

            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                    String.format("%s edited!", entry.getName()));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!retrieveEntryCommand.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return state;
    }

    private static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command                 ;Input Format                                               ");
        TablePrinter.addRow("1.;New entry;entry new /time {HHMM} /desc {string} /cat {category} -[i/e] "
            + "/amt {Double, 2 decimal places}");
        TablePrinter.addRow("2.;Edit entry;entry edit /id {integer} {param-type/parameter to edit}");
        TablePrinter.addRow("3.;list entries;entry list");
        TablePrinter.addRow("4.;delete entry;entry delete /id {integer}");
        TablePrinter.addRow("5.;list transaction categories;cat");
        TablePrinter.addRow("6.;exit to manual tracker;exit");
        TablePrinter.printList();
    }

    private static void printValidCategories() {
        TablePrinter.setTitle("List of Valid Categories");
        TablePrinter.addRow("Category;Input");
        for (String i : CategoryMap.inputToCategoryMap.keySet()) {
            TablePrinter.addRow(String.format("%s;%s", i, CategoryMap.getCategoryFromInput(i)));
        }
        TablePrinter.printList();
    }

    private static FiniteStateMachine.State handleInvalidState() {
        System.out.println("You dun goof bro uwuuuuuuu");
        return FiniteStateMachine.State.MAIN_MENU;
    }


}
