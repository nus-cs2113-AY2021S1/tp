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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditEntryCommand extends Command {
    private static final Pattern EXERCISE_FORMAT =
            Pattern.compile("(?<index>\\d+)\\s*(?<exerciseName>\\S+)\\s*/\\s*(?<calories>\\d+)");
    private static final Pattern FOOD_FORMAT =
            Pattern.compile("(?<index>\\d+)\\s*(?<foodName>\\S+)\\s*/\\s*(?<calories>\\d+)\\s*(?<quantity>\\d+)");
    private final String arguments;

    public EditEntryCommand(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
        try {
            switch (command) {
            case Commands.COMMAND_EXERCISE:
                editExercise(exerciseList, arguments);
                break;
            case Commands.COMMAND_FOOD:
                editFood(foodList, arguments);
                break;
            default:
                Ui.printInvalidCommandError();
                break;
            }
        } catch (NumberFormatException e) {
            Ui.printCustomError("Error: Invalid value entered!");
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

    private void editExercise(ExerciseList exerciseList, String arguments) {
        Matcher matcher = EXERCISE_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            Ui.printFormatError(arguments);
            return;
        }

        if (exerciseList.getSize() == 0) {
            Ui.printCustomError("Error: Exercise list is empty!");
            return;
        }

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > exerciseList.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        String exerciseName = matcher.group("exerciseName").trim();
        exerciseList.getExercise(index - 1).setNameOfExercise(exerciseName);

        int calories = Integer.parseInt(matcher.group("calories").trim());
        if (calories < 0) {
            Ui.printCustomError("Error: Calories cannot be negative!");
            return;
        }
        exerciseList.getExercise(index - 1).setCaloriesBurnt(new Calorie(calories));

        Ui.printCustomMessage("Successfully edited exercise to: " + exerciseName);
    }

    private void editFood(FoodList foodList, String arguments) {
        Matcher matcher = FOOD_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            Ui.printFormatError(arguments);
            return;
        }

        if (foodList.getSize() == 0) {
            Ui.printCustomError("Food list is empty!");
            return;
        }

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > foodList.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        String foodName = matcher.group("foodName").trim();
        foodList.getFood(index - 1).setNameOfFood(foodName);

        int calories = Integer.parseInt(matcher.group("calories").trim());
        if (calories < 0) {
            Ui.printCustomError("Error: Calories cannot be negative!");
            return;
        }

        int quantity = Integer.parseInt(matcher.group("quantity").trim());
        if (quantity < 0) {
            Ui.printCustomError("Error: Quantity cannot be negative!");
            return;
        }
        foodList.getFood(index - 1).setCaloriesInFood(new Calorie(calories * quantity));
        foodList.getFood(index - 1).setAmountOfFood(quantity);

        Ui.printCustomMessage("Successfully edited food to: " + foodName);
    }
}
