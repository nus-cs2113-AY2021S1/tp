package seedu.financeit.recurringtracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.common.exceptions.ItemNotFoundException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class RecurringTracker {
    static String WelcomeMessage = "Welcome to Auto Tracker!";
    static String DirectoryMainMenu = "[ MAIN_MENU -> AUTO_TRACKER ]";
    static RecurringEntryList entries = new RecurringEntryList();

    public static void main() {
        boolean endTracker = false;
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, WelcomeMessage);
        do {
            UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY, DirectoryMainMenu);
            UiManager.printInputPromptMessage();
            String input = UiManager.handleInput();
            CommandPacket packet = new InputParser().parseInput(input);
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
            UiManager.printSpace();
        } while (!endTracker);
    }

    static RecurringEntry handleNewEntry(CommandPacket packet) {
        RecurringEntry entry = new RecurringEntry();
        entry.setRequiredParams(
                "-i or -e",
                "/desc",
                "/amt",
                "/day"
        );
        try {
            entry.handlePacket(packet);
            entries.addItem(entry);
            String entryName = entry.getName();
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                    String.format("%s created!", entryName));
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!entry.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return entry;
    }

    static RecurringEntry handleDeleteEntry(CommandPacket packet) {
        RecurringEntry entry = null;
        entries.setRequiredParams(
                "/id"
        );
        try {
            //User-inputted index ("/id") is parsed, converted to zero-based index
            //and stored in entries.indexToModify (private)
            entries.handleParams(packet);
            entry = (RecurringEntry) entries.getItemAtIndex();
            String entryName = entry.getName();
            entries.removeItemAtIndex();
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                    String.format("%s deleted!", entryName));
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!entries.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }

        return entry;
    }

    static RecurringEntry handleEditEntry(CommandPacket packet) {
        RecurringEntry entry = null;
        entries.setRequiredParams(
                "/id"
        );
        try {
            entries.handleParams(packet);
            entry = (RecurringEntry) entries.getItemAtIndex();
            //Remove "/id" so it doesn't get parsed by entry and lead to
            //printing of UnrecognizedParamMessage
            packet.removeParamsFromMap("/id");
            entry.handleParams(packet);
            UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG,
                    String.format("%s edited!", entry.getName()));
        } catch (InsufficientParamsException | ItemNotFoundException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return entry;
    }

    static void showEntries() {
        entries.printList();
    }

    static void showHelp() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command                 ;Input Format                                               ");
        TablePrinter.addRow("1.;New expenditure (-e) or income (-i);add -[e/i] [-auto] >/desc {DESCRIPTION} "
                + ">/amt {AMOUNT} >/day {DAY_OF_MONTH} >[/notes {NOTES}]");
        TablePrinter.addRow("2.;List entries;list");
        TablePrinter.addRow("3.;Delete entry;delete >/id {INDEX}");
        TablePrinter.addRow("4.;Exit to main menu;exit");
        TablePrinter.printList();
    }

    static void handleInvalidCommand() {
        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid command. Try again.");
    }
}
