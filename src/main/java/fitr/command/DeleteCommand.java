package fitr.command;

import fitr.common.DateManager;
import fitr.exercise.Recommender;
import fitr.exception.FitrException;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static fitr.common.Commands.COMMAND_DELETE;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_GOAL;
import static fitr.common.Messages.ERROR_INDEX_DOES_NOT_EXIST;
import static fitr.common.Messages.ERROR_IN_FILE;
import static fitr.common.Messages.SPLIT_SPACE;

public class DeleteCommand extends Command {
    public DeleteCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String type = command.split(SPLIT_SPACE)[0].toLowerCase();
            if (command.split(SPLIT_SPACE).length != 3 && !type.equals(COMMAND_GOAL)) {
                throw new FitrException();
            }
            switch (type) {
            case COMMAND_EXERCISE: {
                String deletionDate = command.split(SPLIT_SPACE)[1];
                deletionDate = LocalDate.parse(deletionDate, DateManager.formatter).format(DateManager.formatter);
                ExerciseList exerciseList = listManager.getExerciseList();
                boolean isValidDate = false;
                int deletionIndex = Integer.parseInt(command.split(SPLIT_SPACE)[2]);
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
                    Ui.printCustomError("No records found for the specified date!");
                    return;
                }
                Ui.printCustomMessage("The following exercise has been deleted"
                        + " for the " + deletionDate + ": "
                        + listManager.getExercise(deletionIndex).getNameOfExercise());
                listManager.deleteExercise(deletionIndex);
                storageManager.writeExerciseList(listManager.getExerciseList());
                break;
            }
            case COMMAND_FOOD: {
                String deletionDate = command.split(SPLIT_SPACE)[1];
                deletionDate = LocalDate.parse(deletionDate, DateManager.formatter).format(DateManager.formatter);
                FoodList foodList = listManager.getFoodList();
                boolean isValidDate = false;
                int deletionIndex = Integer.parseInt(command.split(SPLIT_SPACE)[2]);
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
                    Ui.printCustomError("No records found for the specified date!");
                    return;
                }
                Ui.printCustomMessage("The following food has been deleted"
                        + " for the " + deletionDate + ": "
                        + listManager.getFood(deletionIndex).getFoodName());
                listManager.deleteFood(deletionIndex);
                storageManager.writeFoodList(listManager.getFoodList());
                break;
            }
            case COMMAND_GOAL: {
                if (command.split(SPLIT_SPACE).length < 2) {
                    Ui.printCustomError("Sorry, deletion index cannot be empty!");
                    return;
                }
                if (!command.split(SPLIT_SPACE, 2)[1].matches("\\d+")) {
                    Ui.printCustomError("Sorry, deletion index must be a positive number");
                    return;
                }
                int deletionIndex = Integer.parseInt(command.split(SPLIT_SPACE, 2)[1]);
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
            Ui.printCustomError(ERROR_INDEX_DOES_NOT_EXIST);
        } catch (NumberFormatException e) {
            Ui.printCustomError("Sorry, index deletion must be a positive number");
        } catch (IOException e) {
            Ui.printCustomError(ERROR_IN_FILE);
        } catch (FitrException e) {
            Ui.printFormatError(COMMAND_DELETE);
        } catch (DateTimeParseException e) {
            Ui.printCustomError("Invalid date entered!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
