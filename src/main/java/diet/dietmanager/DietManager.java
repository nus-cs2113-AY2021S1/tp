package diet.dietmanager;

import logic.commands.Command;
import logic.commands.CommandLib;
import diet.dietsession.DietSession;
import exceptions.ExceptionHandler;
import exceptions.InvalidCommandWordException;
import exceptions.InvalidDateFormatException;
import exceptions.SchwarzeneggerException;
import exceptions.diet.InvalidSearchDateException;
import exceptions.profile.InvalidCommandFormatException;
import logger.SchwarzeneggerLogger;
import logic.commands.CommandResult;
import logic.parser.DietManagerParser;
import storage.diet.DietStorage;
import ui.diet.dietmanager.DietManagerUi;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.Constants.COMMAND_WORD_END;

//@@author CFZeon
/**
 * A class that is responsible for interacting with user in Diet Manager.
 */
public class DietManager {

    private CommandLib cl;
    private DietManagerParser parser;
    private DietManagerUi dietManagerUi;
    private DietStorage storage;
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();

    /**
     * Constructs DietManager and initializes command library for dietManager.
     */
    public DietManager() {
        storage = new DietStorage();
        cl = new CommandLib();
        cl.initDietManagerCl();
        parser = new DietManagerParser();
        dietManagerUi = new DietManagerUi();
    }

    /**
     * Starts diet manager to read user input.
     */
    public void start() {
        dietManagerUi.printOpening("Diet Menu");
        String input = dietManagerUi.getCommand("Diet Menu");
        assert input != null : "Null input before input loop";
        inputLoop(input);
        dietManagerUi.printReturning("Main Menu");
    }

    /**
     * Loops the command processing until user types "end".
     *
     * @param input user input String
     */
    private void inputLoop(String input) {
        while (!input.equals(COMMAND_WORD_END)) {
            try {
                processCommand(input);
            } catch (SchwarzeneggerException e) {
                dietManagerUi.showToUser(ExceptionHandler.handleCheckedExceptions(e));
            } catch (Exception e) {
                dietManagerUi.showToUser(ExceptionHandler.handleUncheckedExceptions(e));
            }
            input = dietManagerUi.getCommand("Diet Menu");
        }
    }

    /**
     * Processes the user input to interpret correct command words.
     *
     * @param input user input for command.
     * @throws InvalidCommandWordException handles InvalidCommandWordException.
     * @throws InvalidDateFormatException handles invalid date input
     * @throws InvalidSearchDateException handles invalid search date
     */
    public void processCommand(String input) throws InvalidCommandWordException, InvalidDateFormatException,
            InvalidSearchDateException {
        String[] commParts = parser.parseCommand(input.trim());
        try {
            Command command = cl.getCommand(commParts[0]);
            CommandResult commandResult = command.execute(commParts[1].trim(), storage);
            assert commandResult != null : "commandResult is not null when command executes properly";
            dietManagerUi.showToUser(commandResult.getFeedbackMessage());
        } catch (ArrayIndexOutOfBoundsException | InvalidCommandFormatException e) {
            logger.log(Level.WARNING, "Invalid command in diet session");
            throw new InvalidCommandWordException();
        }
    }

    /**
     * Gets the total calories of the diet sessions on the specified date.
     *
     * @param savePath path to storage folder
     * @param date date to find total calories
     * @return total calories
     */
    public double getDateTotalCalories(String savePath, LocalDate date) {
        double totalCalories = 0;
        File folder = new File(savePath);
        File[] listOfFiles = folder.listFiles();
        assert folder.exists() : "save folder must exist before getting total calories";
        try {
            // if date on file is same as input date, add to TotalCalories
            for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
                DietSession ds = storage.readDietSession(savePath, listOfFiles[i].getName());
                if (ds.getDate().equals(date)) {
                    totalCalories += ds.getTotalCalories();
                }
            }
            logger.log(Level.INFO, "Calculated total calories so far today");
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "No instances of diet sessions saved");
        }
        return totalCalories;
    }
}
