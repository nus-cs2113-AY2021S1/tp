package seedu.financeit.datatrackers.recurringtracker;

//@@author Artemis-Hunt
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.datatrackers.recurringtracker.recurringhandlers.RetrieveEntryHandler;
import seedu.financeit.parser.InputParser;
import seedu.financeit.datatrackers.recurringtracker.recurringhandlers.CreateEntryHandler;
import seedu.financeit.datatrackers.recurringtracker.recurringhandlers.EditEntryHandler;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class RecurringTracker {
    static String WelcomeMessage = "Welcome to Recurring Tracker!";
    static String DirectoryMainMenu = "[ MAIN_MENU -> RECURRING_TRACKER ]";
    static RecurringEntryList entries = new RecurringEntryList();

    public static RecurringEntryList getRecurringEntryList() {
        return entries;
    }

    public static void execute() {
        boolean endTracker = false;
        UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, WelcomeMessage);
        do {
            UiManager.printWithStatusIcon(Common.PrintType.DIRECTORY, DirectoryMainMenu);
            UiManager.printInputPromptMessage();
            String input = UiManager.handleInput();
            CommandPacket packet = InputParser.getInstance().parseInput(input);
            UiManager.refreshPage();
            switch (packet.getCommandString()) {
            case "add":
                handleNewEntry(packet);
                break;
            case "list":
                showEntries();
                break;
            case "edit":
                handleEditEntry(packet);
                break;
            case "delete":
                handleDeleteEntry(packet);
                break;
            case "commands":
                showHelp();
                break;
            case "exit":
                endTracker = true;
                break;
            default:
                handleInvalidCommand();
            }
        } while (!endTracker);
    }

    public static RecurringEntryList getEntries() {
        return entries;
    }

    public static void loadEntry(CommandPacket packet) {
        handleNewEntry(packet);
    }

    public static RecurringEntry handleNewEntry(CommandPacket packet) {
        RecurringEntry entry = null;
        CreateEntryHandler createEntryHandler = CreateEntryHandler.getInstance();
        try {
            createEntryHandler.handlePacket(packet);
            entry = createEntryHandler.getCurrEntry();
            entries.addItem(entry);
            String entryName = entry.getName();
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                    String.format("%s created!", entryName));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!createEntryHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return entry;
    }

    static RecurringEntry handleDeleteEntry(CommandPacket packet) {
        RecurringEntry entry = null;
        RetrieveEntryHandler retrieveEntryHandler = RetrieveEntryHandler.getInstance();

        try {
            retrieveEntryHandler.handlePacket(packet, entries);
            entry = (RecurringEntry) entries.getItemAtCurrIndex();
            String entryName = entry.getName();
            entries.removeItemAtCurrIndex();
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                    String.format("%s deleted!", entryName));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!retrieveEntryHandler.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }

        return entry;
    }

    static RecurringEntry handleEditEntry(CommandPacket packet) {
        RecurringEntry entry = null;
        RetrieveEntryHandler retrieveEntryHandler = RetrieveEntryHandler.getInstance();
        EditEntryHandler editEntryHandler = EditEntryHandler.getInstance();

        try {
            retrieveEntryHandler.handlePacket(packet, entries);
            entry = (RecurringEntry) entries.getItemAtCurrIndex();
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
        return entry;
    }

    static void showEntries() {
        entries.printList();
    }

    static void showHelp() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command                 ;Input Format                ;"
                            + "Notes                           ");
        TablePrinter.addRow("1.;New expenditure (-e) or income (-i).;add -[e/i] [-auto] >/desc {DESCRIPTION} "
                            + ">/amt {AMOUNT} >/day {DAY_OF_MONTH} >[/notes {NOTES}];Use -auto for "
                            + "income/expenses that are auto-credited into/auto-deducted from bank "
                            + "account/credit card");
        TablePrinter.addRow("2.;Edit entry;edit /id {INDEX} {parameter to edit};");
        TablePrinter.addRow("3.;List entries;list;");
        TablePrinter.addRow("4.;Delete entry;delete /id {INDEX};");
        TablePrinter.addRow("5.;Exit to main menu;exit;");
        TablePrinter.printList();
    }

    static void handleInvalidCommand() {
        UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Invalid command. Try again.");
    }
}
