package fitr.command;

import fitr.Recommender;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
        try {
            command = command.split(" ", 2)[1];
            String type = command.split(" ", 2)[0];
            if (type.equals("food")) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + foodList.getFood(deletionIndex - 1).getFoodName());
                foodList.deleteFood(deletionIndex - 1);
            } else if (type.equals("exercise")) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + exerciseList.getExercise(deletionIndex - 1).getNameOfExercise());
                exerciseList.deleteExercise(deletionIndex - 1);
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printCustomError("Sorry that index does not exist in the list");
        } catch (NumberFormatException e) {
            Ui.printCustomError("Sorry index deletion must be an number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
