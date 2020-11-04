package seedu.smarthomebot;

import seedu.smarthomebot.logic.commands.Command;
import seedu.smarthomebot.logic.commands.CommandResult;
import seedu.smarthomebot.logic.commands.ExitCommand;
import seedu.smarthomebot.commons.Messages;
import seedu.smarthomebot.logic.parser.Parser;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.storage.ReadStorageFile;
import seedu.smarthomebot.storage.WriteStorageFile;
import seedu.smarthomebot.ui.TextUi;


/**
 * Entry point of the SmartHome application.
 * Initializes the application and starts the interaction with the user.
 */

public class Main {

    private TextUi ui = new TextUi();
    private final ApplianceList applianceList = new ApplianceList();
    private final LocationList locationList = new LocationList();
    private WriteStorageFile writeFile = new WriteStorageFile(applianceList, locationList);
    private ReadStorageFile readFile = new ReadStorageFile(applianceList, locationList);

    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Runs the program until termination.
     */
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
        readFile.execute();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.printToUser(Messages.MESSAGE_EXPORT);
        ui.showGoodByeMessage();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the bye command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            writeFile.execute();
            if (result != null) {
                ui.printResultToUser(result);
            }
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(applianceList, locationList);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            ui.printToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
