package seedu.smarthomebot;

import seedu.smarthomebot.commands.Command;
import seedu.smarthomebot.commands.ExitCommand;
import seedu.smarthomebot.data.ApplianceList;
import seedu.smarthomebot.data.HomeLocations;
import seedu.smarthomebot.ui.TextUi;
import seedu.smarthomebot.storage.StorageFile;

import static seedu.smarthomebot.common.Messages.MESSAGE_EXPORT;


/**
 * Entry point of the SmartHome application.
 * Initializes the application and starts the interaction with the user.
 */

public class SmartHomeBot {

    private static TextUi ui = new TextUi();
    private ApplianceList appliances = new ApplianceList();
    private HomeLocations homeLocations = new HomeLocations();
    private StorageFile storage = new StorageFile(appliances, homeLocations);

    public static void main(String[] args) {
        new SmartHomeBot().run();
    }

    /** Runs the program until termination. */
    private void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Initialise the import of data from the text file,
     * and prints the welcome message.
     */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();
        storage.readFile();
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showToUser(MESSAGE_EXPORT);
        ui.showGoodByeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the bye command. */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            command.setData(appliances, homeLocations);
            command.execute();
            storage.writeToFile();
        } while (!ExitCommand.isExit(command));
    }

}
