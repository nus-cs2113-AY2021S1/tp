package fitr.command;

import fitr.Recommender;
import fitr.exception.FitrException;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
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

            String type = command.split(" ")[0];
            if (command.split(" ").length != 3 && !type.equals(COMMAND_GOAL)) {
                throw new FitrException();
            }
            switch (type) {
            case COMMAND_EXERCISE: {
                String deletionDate = command.split(" ")[1];
                ExerciseList exerciseList = listManager.getExerciseList();
                boolean isValidDate = false;
                int deletionIndex = Integer.parseInt(command.split(" ")[2]);
                for (int i = 0; i < exerciseList.getSize(); i++) {
                    if (exerciseList.getExercise(i).getDate().equals(deletionDate)) {
                        isValidDate = true;
                        deletionIndex += i - 1;
                        if (!exerciseList.getExercise(deletionIndex).getDate().equals(deletionDate)) {
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    }
                }
                if (!isValidDate) {
                    throw new FitrException();
                }
                Ui.printCustomMessage("The following exercise has been deleted"
                        + " for the " + deletionDate + ": "
                        + listManager.getExercise(deletionIndex).getNameOfExercise());
                listManager.deleteExercise(deletionIndex);
                storageManager.writeExerciseList(listManager.getExerciseList());
                break;
            }
            case COMMAND_FOOD: {
                String deletionDate = command.split(" ")[1];
                FoodList foodList = listManager.getFoodList();
                boolean isValidDate = false;
                int deletionIndex = Integer.parseInt(command.split(" ")[2]);
                for (int i = 0; i < foodList.getSize(); i++) {
                    if (foodList.getFood(i).getDate().equals(deletionDate)) {
                        isValidDate = true;
                        deletionIndex += i - 1;
                        if (!foodList.getFood(deletionIndex).getDate().equals(deletionDate)) {
                            throw new IndexOutOfBoundsException();
                        }
                        break;
                    }
                }
                if (!isValidDate) {
                    throw new FitrException();
                }
                Ui.printCustomMessage("The following exercise has been deleted"
                        + " for the " + deletionDate + ": "
                        + listManager.getFood(deletionIndex).getFoodName());
                listManager.deleteFood(deletionIndex);
                storageManager.writeFoodList(listManager.getFoodList());
                break;
            }
            case COMMAND_GOAL: {
                int deletionIndex = Integer.parseInt(command.split(" ", 2)[1]);
                Ui.printCustomMessage("The following has been deleted from the list of goals: "
                        + listManager.getGoal(deletionIndex - 1).getDescription());
                listManager.deleteGoal(deletionIndex - 1);
                storageManager.writeGoalList(listManager.getGoalList(), listManager.getFoodList(),
                        listManager.getExerciseList(), user);
                break;
            }
            default:
                throw new FitrException();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printCustomError("Sorry,that index does not exist in the list");
        } catch (NumberFormatException e) {
            Ui.printCustomError("Sorry, index deletion must be a positive number");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, you have keyed in an invalid format or date");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
