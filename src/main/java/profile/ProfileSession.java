package profile;

import exceptions.EndException;
import exceptions.ExceptionHandler;
import exceptions.SchwarzeneggerException;
import logger.SchwarzeneggerLogger;
import logic.commands.Command;
import logic.commands.CommandLib;
import logic.commands.CommandResult;
import logic.parser.CommonParser;
import storage.profile.ProfileStorage;
import ui.CommonUi;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static logic.parser.CommonParser.COMMAND_ARGS_INDEX;
import static logic.parser.CommonParser.COMMAND_TYPE_INDEX;

//@@author tienkhoa16

/**
 * A class that is responsible for interacting with user in Profile Session.
 */
public class ProfileSession {
    private static ProfileSession singleInstance = null;
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    private CommonUi ui;
    private CommonParser parser;
    private ProfileStorage storage;
    private CommandLib cl;

    /**
     * Constructs ProfileSession object with customised path to save data file.
     *
     * @param pathToProfileFolder Path to profile data folder.
     * @param pathToProfileFile Path to profile data file.
     */
    private ProfileSession(Path pathToProfileFolder, Path pathToProfileFile) {
        logger.log(Level.INFO, "initialising ProfileSession object");
        ui = new CommonUi();
        storage = new ProfileStorage(pathToProfileFolder, pathToProfileFile);
        parser = new CommonParser();
        cl = new CommandLib();
        cl.initProfileSessionCl();
    }

    /**
     * Gets the single instance of ProfileSession class.
     *
     * @param pathToProfileFolder Path to profile data folder.
     * @param pathToProfileFile Path to profile data file.
     * @return Single instance of ProfileSession class.
     */
    public static ProfileSession getInstance(Path pathToProfileFolder, Path pathToProfileFile) {
        if (singleInstance == null) {
            singleInstance = new ProfileSession(pathToProfileFolder, pathToProfileFile);
        }
        return singleInstance;
    }

    /**
     * Runs Profile Session.
     */
    public void run() {
        start();
        runCommandLoopTillEnd();
    }

    /**
     * Starts up Profile Session.
     */
    private void start() {
        logger.log(Level.INFO, "starting profile session");
        ui.printOpening("Profile Menu");
    }

    /**
     * Gets user's command and executes repeatedly until user requests to exit Profile Session.
     */
    private void runCommandLoopTillEnd() {
        logger.log(Level.INFO, "executing profile session loop");

        while (true) {
            String userCommand = ui.getCommand("Profile Menu");
            assert userCommand != null : "input should not be null before process loop";

            try {
                CommandResult result = processCommand(userCommand);
                ui.showToUser(result.getFeedbackMessage());
            } catch (SchwarzeneggerException e) {
                ui.showToUser(ExceptionHandler.handleCheckedExceptions(e));

                if (e instanceof EndException) {
                    break;
                }
            } catch (Exception e) {
                ui.showToUser(ExceptionHandler.handleUncheckedExceptions(e));
            }
        }
        logger.log(Level.INFO, "exiting profile session loop");
    }

    /**
     * Processes and displays command execution result to user.
     *
     * @param userCommand User's trimmed input.
     * @return Result after processing command.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    public CommandResult processCommand(String userCommand) throws SchwarzeneggerException {
        String[] commParts = parser.parseCommand(userCommand);
        assert commParts != null : "parsed array should not be null before process loop";

        Command command = cl.getCommand(commParts[COMMAND_TYPE_INDEX]);
        assert command != null : "command object should not be null null";

        CommandResult result = command.execute(commParts[COMMAND_ARGS_INDEX], storage);
        assert result != null : "command result object should not be null null";

        return result;
    }
}
