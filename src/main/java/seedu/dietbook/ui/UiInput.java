package seedu.dietbook.ui;

import seedu.dietbook.logger.MainLogger;
import seedu.dietbook.exception.DietException;

import java.util.Scanner;
import java.util.logging.Level;

/**
 * Represents a text user interface that deals with taking in user commands.
 * A <code>UiInput</code> objects deals with user interaction by taking in user inputs and processing them.
 */
public class UiInput {

    private static Scanner scanner = new Scanner(System.in);

    private final MainLogger mainLogger;

    private final UiHelper uiHelper;

    /**
     * Constructs a <code>UiInput</code> object.
     */
    UiInput() {
        uiHelper = new UiHelper();
        mainLogger = new MainLogger(UiInput.class.getName());
    }

    /**
     * Returns the non-empty user command that has been trimmed.
     *
     * @return The non-empty user command that has been trimmed.
     */
    String getCommand() throws DietException {
        String command = readCommand();
        String processedCommand = processCommand(command);
        mainLogger.log(Level.FINE, "Processed user command: " + processedCommand);
        return processedCommand;
    }

    /**
     * Reads in and returns the user command.
     *
     * @return The user command.
     */
    String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the trimmed user command if its is not empty, else a DietException is thrown.
     *
     * @param command The user command.
     * @return The trimmed user command if it is not empty.
     * @throws DietException If the the user command is empty after trimming.
     */
    String processCommand(String command) throws DietException {
        mainLogger.log(Level.FINE, "User command to process: " + command);
        if (uiHelper.isEmptyString(command)) {
            mainLogger.log(Level.WARNING, "Command is empty!");
            throw new DietException("Command is empty!");
        }
        return uiHelper.trimString(command);
    }
}
