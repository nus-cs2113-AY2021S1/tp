package workout.workoutmanager.command;

import workout.workoutmanager.ExitException;
import ui.workout.workoutmanager.WorkoutManagerUi;

public class ByeWS extends Command {

    @Override
    public void execute(String[] args) {
        logger.info("bye command carried out");
        WorkoutManagerUi.printBye();
        throw new ExitException();
    }
}
