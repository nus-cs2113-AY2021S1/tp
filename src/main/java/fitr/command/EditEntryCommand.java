package fitr.command;

import fitr.Calorie;
import fitr.Recommender;
import fitr.common.Commands;
import fitr.common.Messages;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class EditEntryCommand extends Command {
    private final String index;

    public EditEntryCommand(String arguments, String index) {
        command = arguments;
        this.index = index;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
        try {
            switch (command) {
            case Commands.COMMAND_EXERCISE:
                editExercise(exerciseList, Integer.parseInt(index));
                break;
            case Commands.COMMAND_FOOD:
                editFood(foodList, Integer.parseInt(index));
                break;
            default:
                Ui.printInvalidCommandError();
                break;
            }
        } catch (NumberFormatException e) {
            Ui.printCustomError("Error: Invalid index entered!");
        }

        try {
            storageManager.writeExerciseList(exerciseList);
            storageManager.writeFoodList(foodList);
        } catch (IOException e) {
            Ui.printCustomMessage(Messages.MISSING_FILE);

        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void editExercise(ExerciseList exerciseList, int index) {
        if (exerciseList.getSize() == 0) {
            Ui.printCustomError("Error: Exercise list is empty!");
            return;
        }

        if (index <= 0 || index > exerciseList.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        Ui.printCustomMessage("Editing: " + exerciseList.getExercise(index - 1).getNameOfExercise());
        Ui.printCustomMessage("Enter new exercise name: "
                + "[previous: " + exerciseList.getExercise(index - 1).getNameOfExercise() + "]");
        String newExerciseName = Ui.read();
        exerciseList.getExercise(index - 1).setNameOfExercise(newExerciseName);

        Ui.printCustomMessage("Enter new calories "
                + "[previous: " + exerciseList.getExercise(index - 1).getCalories() + "]");
        int newCalories = Integer.parseInt(Ui.read());
        exerciseList.getExercise(index - 1).setCaloriesBurnt(new Calorie(newCalories));

        Ui.printCustomMessage("Successfully edited exercise!");
    }

    private void editFood(FoodList foodList, int index) {
        if (foodList.getSize() == 0) {
            Ui.printCustomError("Food list is empty!");
            return;
        }

        if (index <= 0 || index > foodList.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        Ui.printCustomMessage("Editing: " + foodList.getFood(index - 1).getFoodName());
        Ui.printCustomMessage("Enter new food name: "
                + "[previous: " + foodList.getFood(index - 1).getFoodName() + "]");
        String newFoodName = Ui.read();
        foodList.getFood(index - 1).setNameOfFood(newFoodName);

        Ui.printCustomMessage("Enter new calories "
                + "[previous: " + foodList.getFood(index - 1).getCalories() + "]");
        int newCalories = Integer.parseInt(Ui.read());
        foodList.getFood(index - 1).setCaloriesInFood(new Calorie(newCalories));

        Ui.printCustomMessage("Enter new quantity "
                + "[previous: " + foodList.getFood(index - 1).getAmountOfFood() + "]");
        int newFoodQuantity = Integer.parseInt(Ui.read());
        foodList.getFood(index - 1).setAmountOfFood(newFoodQuantity);

        Ui.printCustomMessage("Successfully edited food!");
    }
}
