package seedu.financeit.recurringtracker;

import seedu.financeit.common.Constants;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;

public class RecurringTracker {
    static String WelcomeMessage = "Welcome to Auto Tracker!";
    static String DirectoryMainMenu = "[ MAIN_MENU -> AUTO_TRACKER ]";

    public static void main() {
        boolean endTracker = false;
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, WelcomeMessage);
        UiManager.printInputPromptMessage();
        do {
            String input = UiManager.handleInput();
            CommandPacket packet = new InputParser().parseInput(input);
            UiManager.refreshPage();
            switch (packet.getCommandString()) {
            case "add":
                handleAddEntry();
                break;
            case "list":
                handleShowEntries();
                break;
            case "exit":
                endTracker = true;
                break;
            default:
                handleInvalidCommand();
            }
        } while(!endTracker);
    }

    static void handleAddEntry() {

    }

    static void handleShowEntries() {

    }

    static void handleInvalidCommand() {
        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid command. Try again.");
    }
}
