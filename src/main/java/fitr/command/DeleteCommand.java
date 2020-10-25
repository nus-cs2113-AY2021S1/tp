package fitr.command;

import fitr.Recommender;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_GOAL;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
        try {
            String type = command.split(" ", 2)[0];
            if (type.equals(COMMAND_FOOD)) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + foodList.getFood(deletionIndex - 1).getFoodName());
                foodList.deleteFood(deletionIndex - 1);
                storageManager.writeFoodList(foodList);
            } else if (type.equals(COMMAND_EXERCISE)) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + exerciseList.getExercise(deletionIndex - 1).getNameOfExercise());
                exerciseList.deleteExercise(deletionIndex - 1);
                storageManager.writeExerciseList(exerciseList);
            } else if (type.equals(COMMAND_GOAL)) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of goals:\n\t"
                        + goalList.getGoal(deletionIndex - 1).getDescription());
                goalList.deleteGoal(deletionIndex - 1);
                storageManager.writeGoalList(goalList);
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printCustomError("Sorry that index does not exist in the list");
        } catch (NumberFormatException e) {
            Ui.printCustomError("Sorry index deletion must be an number");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
