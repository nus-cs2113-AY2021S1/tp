package workout.workoutsession;

import logic.commands.Command;
import logic.commands.CommandLib;
import exceptions.ExceptionHandler;
import exceptions.InvalidCommandWordException;
import logger.SchwarzeneggerLogger;
import logic.commands.CommandResult;
import logic.parser.WorkoutSessionParser;
import storage.workout.WorkoutSessionStorage;
import ui.CommonUi;
import ui.workout.workoutsession.WorkoutSessionUi;
import models.ExerciseList;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author yujinyang1998
/**
 * A class that is responsible for interacting with user in a Workout Session.
 */
public class WorkoutSession {
    private static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    private String filePath = null;
    private boolean[] endWorkoutSession;
    public ExerciseList exerciseList;
    private boolean isNew;
    private int index;

    private transient CommandLib cl;
    private final WorkoutSessionStorage workoutSessionStorage;
    private CommonUi ui;

    /**
     * Constructs WorkoutSession object.
     *
     * @param filePath Path to data file.
     * @param isNew New or existing file.
     * @param index Index of this WorkoutSession in the list of WorkoutSession.
     */
    public WorkoutSession(String filePath, boolean isNew, int index) {
        this.filePath = filePath;
        this.exerciseList = new ExerciseList();
        this.workoutSessionStorage = new WorkoutSessionStorage();
        this.endWorkoutSession = new boolean[1];
        this.ui = new CommonUi();
        this.isNew = isNew;
        this.index = index;
    }

    private void setEndWorkoutSessionF() {

        this.endWorkoutSession[0] = false;
    }

    /**
     * Starts workout session.
     */
    public void workoutSessionStart() {

        setEndWorkoutSessionF();
        logger.log(Level.INFO, "starting workout session");
        this.cl = new CommandLib();
        cl.initWorkoutSessionCl();

        try {
            workoutSessionStorage.readFileContents(filePath, exerciseList);
        } catch (FileNotFoundException e) {
            ui.showToUser(WorkoutSessionUi.PRINT_ERROR);
        }

        while (!endWorkoutSession[0]) {
            try {
                if (isNew) {
                    workoutSessionProcessCommand(ui.getCommand("Workout Menu > New Workout Session"));
                } else {
                    workoutSessionProcessCommand(ui.getCommand("Workout Menu > Workout Session " + index));
                }
            } catch (NullPointerException e) {
                ui.showToUser(WorkoutSessionUi.EMPTY_INPUT_ERROR);
            } catch (InvalidCommandWordException e) {
                ui.showToUser(ExceptionHandler.handleCheckedExceptions(e));
            }
        }
    }

    private void workoutSessionProcessCommand(String input) throws NullPointerException, InvalidCommandWordException {
        String[] commParts = WorkoutSessionParser.workoutSessionParser(input.trim());
        Command command = cl.getCommand(commParts[0]);
        CommandResult commandResult = command.execute(commParts, exerciseList, filePath, workoutSessionStorage,
                endWorkoutSession);
        if (commandResult.getFeedbackMessage().compareTo("") != 0) {
            ui.showToUser(commandResult.getFeedbackMessage());
        }
    }

}
