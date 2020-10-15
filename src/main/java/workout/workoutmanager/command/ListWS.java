package workout.workoutmanager.command;

import storage.workout.WorkOutManagerStorage;

public class ListWS implements Command {
    @Override
    public void execute(String[] args) {
        WorkOutManagerStorage.list(args);
    }
}
