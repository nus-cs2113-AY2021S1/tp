package workout.workoutmanager.command;

import storage.workout.WorkOutManagerStorage;
import workout.workoutmanager.WorkoutManagerUI;

public class DeleteWS implements Command {

    @Override
    public void execute(String[] args) {
        int index = -1;
        try {
            index = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Input is not a number");
        } catch (NullPointerException e) {
            System.out.println("Insufficient arguments are given.");
        }
        WorkOutManagerStorage.delete(index);
        WorkoutManagerUI.printDeleteResponse();
    }
}
