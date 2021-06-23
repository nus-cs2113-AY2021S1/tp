package logic.commands.main;

import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import workout.workoutmanager.WorkoutManager;

//@@author tienkhoa16

/**
 * A representation of the command for redirecting to Workout Menu from Main Menu.
 */
public class ToWorkout extends Command {

    /**
     * Executes redirecting to Workout Menu command from Main Menu.
     *
     * @param args User's input.
     * @return Redirecting to Workout Menu message.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        assert args != null : "arguments cannot be null";
        super.execute(args);

        if (!args.isEmpty()) {
            ui.showWarning("\"workout\" command does not take in parameters");
        }

        WorkoutManager workoutManager = new WorkoutManager();
        workoutManager.start();

        return new CommandResult("", ExecutionResult.OK);
    }
}
