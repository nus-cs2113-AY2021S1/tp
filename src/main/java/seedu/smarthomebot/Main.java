package seedu.smarthomebot;

import seedu.smarthomebot.logic.commands.Command;
import seedu.smarthomebot.logic.commands.CommandResult;
import seedu.smarthomebot.logic.commands.ExitCommand;
import seedu.smarthomebot.logic.parser.Parser;
import seedu.smarthomebot.data.appliance.ApplianceList;
import seedu.smarthomebot.data.location.LocationList;
import seedu.smarthomebot.storage.ReadStorageFile;
import seedu.smarthomebot.storage.WriteStorageFile;
import seedu.smarthomebot.ui.TextUi;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@@author Ang_Cheng_Jun
/**
 * Entry point of the SmartHome application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    private TextUi ui = new TextUi();
    private static final String FILE_PATH = "data/SmartHomeBot.txt";
    private final ApplianceList applianceList = new ApplianceList();
    private final LocationList locationList = new LocationList();
    private WriteStorageFile writeFile = new WriteStorageFile(FILE_PATH, applianceList, locationList);
    private ReadStorageFile readFile = new ReadStorageFile(FILE_PATH, applianceList, locationList);
    private final Logger logger = Logger.getLogger("SmartHomeBotLogger");

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
     * Initialises the import of data from the text file,
     * and prints the Welcome message.
     */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage();
        setupLogger();
        readFile.execute();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
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

    /**
     * Executes the user command and prints the result.
     */
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

    //@@author zongxian-ctrl
    /**
     * Creates the default logger with level and initialise log file.
     */
    private void setupLogger() {
        File myObj = new File("./log");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        try {
            if (!myObj.exists()) {
                myObj.mkdir();
            }
            FileHandler fileHandler = new FileHandler("log/SmartHomeBotLog.txt", false);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
