package fitr.command;

import fitr.Recommender;
import fitr.list.ListManager;
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
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String type = command.split(" ", 2)[0].toLowerCase();
            if (type.equals(COMMAND_FOOD)) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + listManager.getFood(deletionIndex - 1).getFoodName());
                listManager.deleteFood(deletionIndex - 1);
                storageManager.writeFoodList(listManager.getFoodList());
            } else if (type.equals(COMMAND_EXERCISE)) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of food consumed: "
                        + listManager.getExercise(deletionIndex - 1).getNameOfExercise());
                listManager.deleteExercise(deletionIndex - 1);
                storageManager.writeExerciseList(listManager.getExerciseList());
            } else if (type.equals(COMMAND_GOAL)) {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of goals:\n\t"
                        + listManager.getGoal(deletionIndex - 1).getDescription());
                listManager.deleteGoal(deletionIndex - 1);
                storageManager.writeGoalList(listManager.getGoalList(), listManager.getFoodList(), listManager.getExerciseList(), user);
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
