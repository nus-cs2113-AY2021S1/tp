package seedu.quotesify;

import seedu.quotesify.commands.Command;
import seedu.quotesify.lists.ListManager;
import seedu.quotesify.parser.Parser;
import seedu.quotesify.store.Storage;
import seedu.quotesify.ui.TextUi;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Main entry point for Quotesify.
 */
public class Quotesify {

    private TextUi ui;
    private Parser parser;

    private final Logger logger = Logger.getLogger("QuotesifyLogger");
    private final Storage storage;
    private final String saveFileLocation = "/data/quotesify.json";

    /**
     * Default constructor for Quotesify.
     */
    public Quotesify() {
        ui = new TextUi();
        parser = new Parser();

        setupLogger();
        ListManager.initialiseAllLists();
        storage = new Storage(saveFileLocation);
        storage.load();
    }

    /**
     * Prints a welcome message and a random quote.
     */
    public void start() {
        ui.showWelcomeMessage();
        ui.printRandomQuote();
    }

    /**
     * Prints a random quote and a goodbye message before exiting the program.
     */
    public void exit() {
        ui.printRandomQuote();
        ui.showGoodbyeMessage();
        ui.printDividerLine();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it until an exit command is issued.
     */
    public void runLoopUntilExitCommand() {
        boolean isExit = false;
        while (!isExit) {
            ui.printDividerLine();
            String userCommandText = ui.getUserCommand();
            Command command = parser.parseUserCommand(userCommandText);
            ui.printDividerLine();
            if (command == null) {
                ui.printInvalidQuotesifyCommand();
                continue;
            }
            command.execute(ui, storage);
            isExit = command.isExit();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runLoopUntilExitCommand();
        exit();
    }

    /**
     * Program main method.
     *
     * @param args user specified arguments
     */
    public static void main(String[] args) {
        new Quotesify().run();
    }

    /**
     * Sets up the default logger level and initialises the log file.
     */
    private void setupLogger() {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("quotesify.log", true);
            // remove this if you want to view logs in XML format
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
