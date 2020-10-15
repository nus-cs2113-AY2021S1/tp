package workout.workoutmanager.command;

import workout.workoutmanager.ExitException;
import workout.workoutmanager.WorkoutManagerUI;

public class ByeWS implements Command {

    @Override
    public void execute(String[] args) {
        WorkoutManagerUI.printBye();
        throw new ExitException();
    }
}
