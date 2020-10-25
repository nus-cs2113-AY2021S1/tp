package fitr.command;

import fitr.Recommender;
import fitr.exception.FitrException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_COMPLETE;
import static fitr.common.Commands.COMMAND_GOAL;

public class CompleteGoalCommand extends Command{
    public CompleteGoalCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager, User user, GoalList goalList, Recommender recommender) {
        try {
            if (command.split(" ", 2)[0].equals(COMMAND_GOAL)) {
                command = command.split(" ", 2)[1];
                int completedGoalIndex = Integer.parseInt(command) - 1;
                goalList.getGoal(completedGoalIndex).markAsCompleted();
                storageManager.writeGoalList(goalList, foodList, exerciseList, user);
                Ui.printCustomMessage("Yay! You completed:\n\t"
                        + goalList.getGoal(completedGoalIndex).getDescription());
            } else {
                throw new FitrException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printCustomError("Sorry that index does not exist in the list");
        } catch (NumberFormatException e) {
            Ui.printCustomError("Sorry index deletion must be an number");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        } catch (FitrException e) {
            Ui.printCustomError("Wrong format!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
