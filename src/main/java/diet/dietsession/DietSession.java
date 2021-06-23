package diet.dietsession;

import exceptions.ExceptionHandler;
import exceptions.InvalidCommandWordException;
import exceptions.InvalidDateFormatException;
import logger.SchwarzeneggerLogger;
import logic.commands.Command;
import logic.commands.CommandLib;
import logic.commands.CommandResult;
import logic.parser.DietSessionParser;
import models.Food;
import storage.diet.DietStorage;
import ui.diet.dietsession.DietSessionUi;
import logic.parser.DateParser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.Constants.PATH_TO_DIET_FOLDER;

//@@author zsk612
/**
 * A class that is responsible for interacting with user in Diet Session.
 */
public class DietSession {
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    private final ArrayList<Food> foodList;

    private final String dateInput;
    private final String typeInput;
    private final LocalDate date;
    private boolean isNew;
    private int index;

    private final DietSessionUi dietSessionUi;
    private transient CommandLib cl;
    private final DietStorage storage;
    private final DietSessionParser parser = new DietSessionParser();
    public boolean endDietSession = false;

    /**
     * Constructs DietSession and initialize command library for dietSession.
     *
     * @param typeInput User input for meal type
     * @param dateInput User input for meal date
     * @param isNew Boolean that indicates whether the Diet Session is new or not
     * @param index Integer for the index of the Diet Session
     * @throws InvalidDateFormatException handles invalid date input
     */
    public DietSession(String typeInput, String dateInput, boolean isNew, int index) throws InvalidDateFormatException {
        this.cl = new CommandLib();
        cl.initDietSessionCl();
        this.dateInput = dateInput;
        this.date = DateParser.parseDate(dateInput).toLocalDate();
        this.typeInput = typeInput;
        this.foodList = new ArrayList<>();
        storage = new DietStorage();
        dietSessionUi = new DietSessionUi();
        this.isNew = isNew;
        this.index = index;
    }

    public String getDateInput() {
        return dateInput;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTypeInput() {
        return typeInput;
    }

    public void setEndDietSession(Boolean hasEnded) {
        this.endDietSession = hasEnded;
    }

    /**
     * Starts dietSession and initializes command library for dietSession.
     * @param isNew Boolean that indicates whether the Diet Session is new or not
     * @param index Integer for the index of the Diet Session
     * @throws IOException handles input/output exception
     */
    public void start(boolean isNew, int index) throws IOException {

        logger.log(Level.INFO, "starting diet session");
        this.cl = new CommandLib();
        cl.initDietSessionCl();
        dietSessionUi.printOpening();
        setEndDietSession(false);
        this.isNew = isNew;
        this.index = index;
        // save the file upon creation
        saveToFile(PATH_TO_DIET_FOLDER, storage, this);
        dietSessionInputLoop();
        setEndDietSession(true);
    }

    /**
     * Starts reading user input for dietSession commands.
     */
    private void dietSessionInputLoop() {
        String input = "";

        if (isNew) {
            input = dietSessionUi.getCommand("Diet Menu > New Diet Session");
        } else {
            input = dietSessionUi.getCommand("Diet Menu > Diet Session " + index);
        }

        while (!input.equals("end")) {

            try {
                processCommand(input);
            } catch (NullPointerException e) {
                dietSessionUi.showToUser(ExceptionHandler.handleUncheckedExceptions(e));
                break;
            } catch (InvalidCommandWordException e) {
                dietSessionUi.showToUser(ExceptionHandler.handleCheckedExceptions(e));
            }
            if (isNew) {
                input = dietSessionUi.getCommand("Diet Menu > New Diet Session");
            } else {
                input = dietSessionUi.getCommand("Diet Menu > Diet Session " + index);
            }
        }
    }

    /**
     * Processes user input for dietSession commands.
     *
     * @param input user input for command
     * @throws NullPointerException handles null pointer exception
     * @throws InvalidCommandWordException handles invalid commands
     */
    private void processCommand(String input) throws NullPointerException, InvalidCommandWordException {
        String[] commParts = parser.parseCommand(input);
        Command command = cl.getCommand(commParts[0]);
        CommandResult commandResult = command.execute(commParts[1].trim(), foodList, storage, index);
        dietSessionUi.showToUser(commandResult.getFeedbackMessage());
        saveToFile(PATH_TO_DIET_FOLDER, storage, this);
    }

    /**
     * Calculates the sum of all food calories in diet session.
     *
     * @return sum of calories of food
     */
    public double getTotalCalories() {
        double totalCalories = 0;
        for (int i = 0; i < foodList.size(); i++) {
            totalCalories += foodList.get(i).getCalories();
        }
        return totalCalories;
    }


    /**
     * Constructs method to save changes to storage file.
     *
     * @param filePath string for file path
     * @param storage storage for diet manager
     * @param ds      dietSession that is being changed
     */
    public void saveToFile(String filePath, DietStorage storage, DietSession ds) {
        try {
            storage.init(filePath, ds.getDate().toString() + " " + ds.getTypeInput());
            storage.writeToStorageDietSession(filePath, ds.getDate().toString() + " " + ds.getTypeInput(), ds);
            logger.log(Level.INFO, "Diet session successfully saved");
        } catch (IOException e) {
            logger.log(Level.WARNING, "save profile session failed");
            dietSessionUi.showToUser("Failed to save your diet session!");
        }
    }
}
