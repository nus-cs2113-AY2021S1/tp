package fitr.command;

import fitr.calorie.Calorie;
import fitr.common.DateManager;
import fitr.exception.FitrException;
import fitr.exception.UpperBoundLessThanException;
import fitr.exception.UpperBoundMoreThanException;
import fitr.goal.Goal;
import fitr.exercise.Recommender;
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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.COLOURED_FORMAT_STRING;
import static fitr.common.Messages.ERROR_FORMAT_MESSAGE;
import static fitr.common.Messages.ERROR_GOAL_LESS_THAN_UPPERBOUND;
import static fitr.common.Messages.ERROR_GOAL_MORE_THAN_UPPERBOUND;
import static fitr.common.Messages.ERROR_INVALID_GOAL_TYPE;
import static fitr.common.Messages.ERROR_INVALID_INDEX;
import static fitr.common.Messages.FORMAT_EDIT_EXERCISE;
import static fitr.common.Messages.FORMAT_EDIT_FOOD;
import static fitr.common.Messages.FORMAT_EDIT_GOAL;
import static fitr.common.Messages.KEYWORD_CALORIES;
import static fitr.common.Messages.SPACE_STRING;
import static fitr.common.Messages.SYMBOL_EXERCISE;
import static fitr.common.Messages.SYMBOL_FOOD;
import static fitr.common.Messages.SYMBOL_NO;
import static fitr.goal.FormatGoal.formatGoal;

public class EditEntryCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(EditEntryCommand.class.getName());
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
        } catch (NumberFormatException | FitrException e) {
            Ui.printCustomError("Invalid value entered!");
        } catch (DateTimeParseException e) {
            Ui.printCustomError("Invalid date entered!");
        } catch (UpperBoundLessThanException e) {
            Ui.printCustomError(ERROR_GOAL_LESS_THAN_UPPERBOUND);
        } catch (UpperBoundMoreThanException e) {
            Ui.printCustomError(ERROR_GOAL_MORE_THAN_UPPERBOUND);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Invalid input!");
        }

        try {
            LOGGER.fine("Writing to exercise, food and goal lists to local storage...");
            storageManager.writeExerciseList(listManager.getExerciseList());
            storageManager.writeFoodList(listManager.getFoodList());
            storageManager.writeGoalList(listManager.getGoalList(), listManager.getFoodList(),
                    listManager.getExerciseList(), user);
            LOGGER.fine("Exercise, food and goal lists successfully written to local storage.");
        } catch (IOException e) {
            Ui.printCustomError(Messages.MISSING_FILE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void editExercise(ExerciseList exerciseList, String arguments) {
        LOGGER.fine("Editing an exercise entry...");
        Matcher matcher = EXERCISE_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            Ui.printCustomError(ERROR_FORMAT_MESSAGE);
            Ui.printCustomMessage(COLOURED_FORMAT_STRING + FORMAT_EDIT_EXERCISE);
            return;
        }

        if (exerciseList.getSize() == 0) {
            Ui.printCustomError("Exercise list is empty!");
            return;
        }

        LocalDate date = LocalDate.parse(matcher.group("date").trim(), DateManager.formatter);
        String formattedDate = date.format(DateManager.formatter);
        ExerciseList filteredExercises = new ExerciseList(exerciseList.filterByDate(formattedDate));

        if (filteredExercises.getSize() == 0) {
            Ui.printCustomError("No exercise entries found on: " + formattedDate);
            return;
        }

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > filteredExercises.getSize()) {
            Ui.printCustomError("Invalid index entered!");
            return;
        }

        String exerciseName = matcher.group("exerciseName").trim();
        if (exerciseName.isBlank()) {
            Ui.printCustomError("Name cannot be empty!");
            return;
        }

        int calories = Integer.parseInt(matcher.group(KEYWORD_CALORIES).trim());

        if (calories < 1 || calories > 10000) {
            Ui.printCustomError("Calories must be between 0 and 10000 (inclusive)!");
            return;
        }

        filteredExercises.getExercise(index - 1).setNameOfExercise(exerciseName);
        filteredExercises.getExercise(index - 1).setCaloriesBurnt(new Calorie(calories));

        Ui.printCustomMessage("Successfully edited exercise to: " + exerciseName + ", calories burnt: " + calories);
    }

    private void editFood(FoodList foodList, String arguments) {
        LOGGER.fine("Editing a food entry...");
        Matcher matcher = FOOD_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            Ui.printCustomError(ERROR_FORMAT_MESSAGE);
            Ui.printCustomMessage(COLOURED_FORMAT_STRING + FORMAT_EDIT_FOOD);
            return;
        }

        if (foodList.getSize() == 0) {
            Ui.printCustomError("Food list is empty!");
            return;
        }

        LocalDate date = LocalDate.parse(matcher.group("date").trim(), DateManager.formatter);
        String formattedDate = date.format(DateManager.formatter);
        FoodList filteredFood = new FoodList(foodList.filterByDate(formattedDate));

        if (filteredFood.getSize() == 0) {
            Ui.printCustomError("No food entries found on: " + formattedDate);
            return;
        }

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > filteredFood.getSize()) {
            Ui.printCustomError("Invalid index entered!");
            return;
        }

        String foodName = matcher.group("foodName").trim();
        if (foodName.isBlank()) {
            Ui.printCustomError("Name cannot be empty!");
            return;
        }

        int calories = Integer.parseInt(matcher.group("calories").trim());

        if (calories < 0 || calories > 10000) {
            Ui.printCustomError("Calories must be between 0 and 10000 (inclusive)!");
            return;
        }

        int quantity = Integer.parseInt(matcher.group("quantity").trim());
        if (quantity < 1 || quantity > 1000) {
            Ui.printCustomError("Quantity must be between 1 and 1000 (inclusive)!");
            return;
        }

        filteredFood.getFood(index - 1).setNameOfFood(foodName);
        filteredFood.getFood(index - 1).setCaloriesInFood(new Calorie(calories * quantity));
        filteredFood.getFood(index - 1).setAmountOfFood(quantity);

        Ui.printCustomMessage("Successfully edited food to: " + foodName
                + ", calories (per qty): " + calories + ", amount: " + quantity);
    }

    private void editGoal(GoalList goalList, String arguments) throws FitrException,
            UpperBoundLessThanException, UpperBoundMoreThanException {
        LOGGER.fine("Editing a goal entry...");
        Matcher matcher = GOAL_FORMAT.matcher(arguments);

        if (!matcher.matches()) {
            Ui.printCustomError(ERROR_FORMAT_MESSAGE);
            Ui.printCustomMessage(COLOURED_FORMAT_STRING + FORMAT_EDIT_GOAL);
            return;
        }

        if (goalList.getSize() == 0) {
            Ui.printCustomError("Goal list is empty!");
            return;
        }

        int index = Integer.parseInt(matcher.group("index").trim());
        if (index <= 0 || index > goalList.getSize()) {
            Ui.printCustomError(ERROR_INVALID_INDEX);
            return;
        }

        String goalDescription = matcher.group("goalDescription").trim();
        String goalType = matcher.group("goalType").trim().toLowerCase();

        if (!(goalType.equals(Commands.COMMAND_EXERCISE) || goalType.equals(Commands.COMMAND_FOOD))) {
            Ui.printCustomError(ERROR_INVALID_GOAL_TYPE);
            return;
        }

        Goal goal = goalList.getGoal(index - 1);
        String createdDate = goal.getCreatedDate();
        goalType = goalType.equals(Commands.COMMAND_EXERCISE) ? SYMBOL_EXERCISE : SYMBOL_FOOD;
        Goal editedGoal = formatGoal(LocalDate.parse(createdDate, DateManager.formatter), goalType, goalDescription);
        goal.setGoal(editedGoal, SYMBOL_NO);

        Ui.printCustomMessage("Successfully edited goal to: [" + editedGoal.getGoalType()
                + CLOSE_SQUARE_BRACKET + SPACE_STRING + editedGoal.getDescription());
    }
}
