package seedu.financeit.datatrackers.entrytracker;

import seedu.financeit.common.CategoryMap;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.DuplicateInputException;
import seedu.financeit.common.exceptions.IncompatibleParamsException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.datatrackers.entrytracker.entryhandlers.CreateEntryHandler;
import seedu.financeit.datatrackers.entrytracker.entryhandlers.EditEntryHandler;
import seedu.financeit.datatrackers.entrytracker.entryhandlers.RetrieveEntryHandler;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.manualtracker.Ledger;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

/**
 * Class to handle routine for manual entry management.
 */
public class EntryTracker {
    private static Ledger currLedger;
    static EntryList entryList;
    private static CommandPacket packet;
    private static boolean isUnderTest = false;
    private static GoalTracker goalTracker = new GoalTracker();
    private static boolean endTracker;

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



    public static void setCurrLedger(Ledger ledger) {
        currLedger = ledger;
        entryList = ledger.entryList;
    }

    public static void execute() {
        endTracker = false;
        while (!endTracker) {
            handleMainMenu();
        }
    }

    public static EntryList getEntryList() {
        return entryList;
    }

    private static void handleMainMenu() {
        String input;

        UiManager.printWithStatusIcon(Common.PrintType.DIRECTORY,
                String.format("[ MAIN_MENU -> MANUAL_TRACKER_MENU -> ENTRY_TRACKER (LEDGER %s)", currLedger));
        UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
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
        case "edit":
            handleEditEntry();
            break;
        case "new":
            handleCreateEntry();
            break;
        case "list":
            handleShowEntry();
            break;
        case "delete":
            handleDeleteEntry();
            break;
        case "exit":
            endTracker = true;
            break;
        case "commands":
            printCommandList();
            break;
        case "cat":
            printValidCategories();
            break;
        default:
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,"Command not recognised. Try again.");
            break;
        }
    }

    static void handleDeleteEntry() {
        RetrieveEntryHandler retrieveEntryHandler = RetrieveEntryHandler.getInstance();
        Entry deletedEntry;
        try {
            // RetrieveEntryCommand instance retrieves the corresponding entry instance
            // from the entryList instance.
            retrieveEntryHandler.handlePacket(packet, entryList);
            deletedEntry = (Entry) entryList.getItemAtCurrIndex();

            // Deletion of entry.
            entryList.removeItemAtCurrIndex();

            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                    String.format("%s deleted!", deletedEntry.getName()));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!retrieveEntryHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
    }

    static void handleShowEntry() {
        entryList.printList();
    }

    static void handleCreateEntry(Boolean... isPrintGoalInput) {
        boolean isPrintGoal = (isPrintGoalInput.length > 0 && isPrintGoalInput[0] == false)
            ? false
            : true;
        CreateEntryHandler createEntryHandler = CreateEntryHandler.getInstance();

        Entry entry;
        try {
            // CreateLedgerCommand instance generates a new Ledger instance
            // from the params specified in the command.
            createEntryHandler.handlePacket(packet);
            entry = createEntryHandler.getCurrEntry();

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

            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                    String.format("%s created!", entry.getName()));
        } catch (InsufficientParamsException | IncompatibleParamsException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } catch (DuplicateInputException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                "Duplicate item already exists in the list; not added!");
        } finally {
            if (!createEntryHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
    }

    static void handleEditEntry() {
        RetrieveEntryHandler retrieveEntryHandler = RetrieveEntryHandler.getInstance();
        EditEntryHandler editEntryHandler = EditEntryHandler.getInstance();

        Entry entry;
        try {
            // RetrieveEntryCommand instance retrieves the corresponding entry instance
            // from the entryList instance.
            retrieveEntryHandler.handlePacket(packet, entryList);
            entry = (Entry) entryList.getItemAtCurrIndex();

            // EditEntryCommand instance edits details of the corresponding ledger instance
            // from the entryList instance.
            editEntryHandler.setEntry(entry);
            editEntryHandler.handlePacket(packet);
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                    String.format("%s edited!", entry.getName()));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!retrieveEntryHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
    }

    private static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command                 ;Input Format                                               ");
        TablePrinter.addRow("1.;New entry;new /time {HHMM} /desc {string} /cat {category} -[i/e] "
            + "/amt {Double, 2 decimal places}");
        TablePrinter.addRow("2.;Edit entry;edit /id {integer} {param-type/parameter to edit}");
        TablePrinter.addRow("3.;list entries;list");
        TablePrinter.addRow("4.;delete entry;delete /id {integer}");
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
}
