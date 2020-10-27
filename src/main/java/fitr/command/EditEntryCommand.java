package fitr.command;

import fitr.Calorie;
import fitr.Goal;
import fitr.Recommender;
import fitr.common.Commands;
import fitr.common.Messages;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fitr.goal.FormatGoal.formatGoal;

public class EditEntryCommand extends Command {
    private static final String EXERCISE_FORMAT_REGEX =
            "(?<date>\\S+)\\s+(?<index>\\d+)\\s+(?<exerciseName>.*)\\s*/\\s*(?<calories>\\d+)";
    private static final String FOOD_FORMAT_REGEX =
            "(?<date>\\S+)\\s+(?<index>\\d+)\\s*(?<foodName>.*)\\s*/\\s*(?<calories>\\d+)\\s+(?<quantity>\\d+)";
    private static final Pattern EXERCISE_FORMAT = Pattern.compile(EXERCISE_FORMAT_REGEX);
    private static final Pattern FOOD_FORMAT = Pattern.compile(FOOD_FORMAT_REGEX);
    private static final Pattern GOAL_FORMAT =
            Pattern.compile("(?<index>\\d+)\\s+(?<goalType>\\S+)\\s+(?<goalDescription>.*)");
    private final String arguments;

    public EditEntryCommand(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            switch (command) {
            case Commands.COMMAND_EXERCISE:
                editExercise(listManager.getExerciseList(), arguments);
                break;
            case Commands.COMMAND_FOOD:
                editFood(listManager.getFoodList(), arguments);
                break;
            case Commands.COMMAND_GOAL:
                editGoal(listManager.getGoalList(), arguments);
                break;
            default:
                Ui.printInvalidCommandError();
                break;
            }
        } catch (NumberFormatException e) {
            Ui.printCustomError("Error: Invalid value entered!");
        }

        try {
            storageManager.writeExerciseList(listManager.getExerciseList());
            storageManager.writeFoodList(listManager.getFoodList());
            storageManager.writeGoalList(listManager.getGoalList(), listManager.getFoodList(),
                    listManager.getExerciseList(), user);
        } catch (IOException e) {
            Ui.printCustomError(Messages.MISSING_FILE);
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

        String date = matcher.group("date").trim();
        ExerciseList filteredExercises = new ExerciseList(exerciseList.filterByDate(date));

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > filteredExercises.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        String exerciseName = matcher.group("exerciseName").trim();
        int calories = Integer.parseInt(matcher.group("calories").trim());

        if (calories < 0) {
            Ui.printCustomError("Error: Calories cannot be negative!");
            return;
        }

        filteredExercises.getExercise(index - 1).setNameOfExercise(exerciseName);
        filteredExercises.getExercise(index - 1).setCaloriesBurnt(new Calorie(calories));

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

        String date = matcher.group("date").trim();
        FoodList filteredFood = new FoodList(foodList.filterByDate(date));

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > filteredFood.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        String foodName = matcher.group("foodName").trim();
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

        filteredFood.getFood(index - 1).setNameOfFood(foodName);
        filteredFood.getFood(index - 1).setCaloriesInFood(new Calorie(calories * quantity));
        filteredFood.getFood(index - 1).setAmountOfFood(quantity);

        Ui.printCustomMessage("Successfully edited food to: " + foodName);
    }

    private void editGoal(GoalList goalList, String arguments) {
        Matcher matcher = GOAL_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            Ui.printFormatError(arguments);
            return;
        }

        if (goalList.getSize() == 0) {
            Ui.printCustomError("Goal list is empty!");
            return;
        }

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > goalList.getSize()) {
            Ui.printCustomError("Error: Invalid index entered!");
            return;
        }

        String goalDescription = matcher.group("goalDescription").trim();
        String goalType = matcher.group("goalType").trim();

        if (!(goalType.equals(Commands.COMMAND_EXERCISE) || goalType.equals(Commands.COMMAND_FOOD))) {
            Ui.printCustomError("Error: Invalid goal type!");
            return;
        }

        Goal goal = goalList.getGoal(index - 1);
        String createdDate = goal.getCreatedDate();
        goalType = goalType.equals(Commands.COMMAND_EXERCISE) ? "E" : "F";
        Goal editedGoal = formatGoal(createdDate, goalType, goalDescription);
        goal.setGoal(editedGoal, "0.0");

        Ui.printCustomMessage("Successfully edited goal to: [" + editedGoal.getGoalType()
                + "] " + editedGoal.getDescription());
    }
}
