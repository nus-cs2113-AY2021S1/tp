package workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandLib;
import logic.commands.CommandResult;
import exceptions.EndException;
import exceptions.SchwarzeneggerException;
import logger.SchwarzeneggerLogger;
import logic.parser.WorkoutManagerParser;
import models.PastRecordList;
import ui.workout.workoutmanager.WorkoutManagerUi;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author wgzesg
/**
 * A class that is responsible for interacting with user in Workout Manager.
 */
public class WorkoutManager {

    private static final Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    private final CommandLib cl;
    private final WorkoutManagerUi ui;

    /**
     * Constructs Workout Manager.
     */
    public WorkoutManager() {
        cl = new CommandLib();
        cl.initWorkoutManagerCl();
        logger.log(Level.INFO, "Initialised workout manager command library");
        ui = new WorkoutManagerUi();
    }

    /**
     * Starts a workout manager. Goes into a REPL until 'end' command is given.
     */
    public void start() {
        logger.log(Level.INFO, "Entered workout manager");
        ui.printOpening("Workout Menu");

        while (true) {

            String command = ui.getCommand("Workout Menu");
            logger.log(Level.INFO, "Received input" + command);

            String[] commParts = WorkoutManagerParser.getInstance().parseCommandKw(command);
            try {
                processCommand(commParts);
            } catch (EndException e) {
                logger.log(Level.INFO, "exiting workout manager");
                break;
            } catch (SchwarzeneggerException e) {
                logger.log(Level.WARNING, "processing SchwarzeneggerException", e);
                ui.showToUser(e.getMessage());
            }
        }

        ui.printReturning("Main Menu");
    }

    private void processCommand(String[] commands) throws SchwarzeneggerException {
        Command command = cl.getCommand(commands[0]);
        CommandResult result = command.execute((commands.length > 1) ? commands[1].trim() : "");
        ui.showToUser(result.getFeedbackMessage());
    }
}
