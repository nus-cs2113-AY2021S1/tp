package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.user.User;
import fitr.ui.Ui;

import static fitr.common.Messages.ERROR_INVALID_VIEW_COMMAND;
import static fitr.common.Messages.EMPTY_FOOD_LIST;
import static fitr.common.Messages.EMPTY_EXERCISE_LIST;
import static fitr.common.Messages.FOOD_LIST_HEADER;
import static fitr.common.Messages.EXERCISE_LIST_HEADER;
import static fitr.common.Messages.CALORIE_CONSUMED_HEADER;
import static fitr.common.Messages.CALORIE_BURNT_HEADER;
import static fitr.common.Messages.NET_CALORIE_HEADER;
import static fitr.common.Messages.BMI_HEADER;
import static fitr.common.Messages.USER_PROFILE_HEADER;
import static fitr.common.Messages.OPEN_SQUARE_BRACKET;
import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.FOOD_HEADER;
import static fitr.common.Messages.SPACE_FORMATTING;
import static fitr.common.Messages.CAL_HEADER;
import static fitr.common.Messages.EXERCISE_HEADER;
import static fitr.common.Messages.BURNT_CAL_HEADER;

import static fitr.common.Commands.COMMAND_VIEW_FOOD;
import static fitr.common.Commands.COMMAND_VIEW_EXERCISE;
import static fitr.common.Commands.COMMAND_VIEW_SUMMARY;
import static fitr.common.Commands.COMMAND_VIEW_BMI;
import static fitr.common.Commands.COMMAND_VIEW_PROFILE;

public class ViewCommand extends Command {

    public ViewCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage, User user) {
        if (command.equalsIgnoreCase(COMMAND_VIEW_FOOD)) {
            viewFood(foodList);
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_EXERCISE)) {
            viewExercise(exerciseList);
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_SUMMARY)) {
            viewSummary(foodList, exerciseList, user);
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_BMI)) {
            viewBmi(user);
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_PROFILE)) {
            viewProfile(user);
        } else {
            Ui.printCustomError(ERROR_INVALID_VIEW_COMMAND);
        }
    }

    //View food
    private void viewFood(FoodList foodList) {
        if (foodList.getSize() == 0) {
            Ui.printCustomMessage(EMPTY_FOOD_LIST);
        } else {
            int index = 0;
            int printIndex = index + 1;
            Ui.printCustomMessage(FOOD_LIST_HEADER);
            while (index < foodList.getSize()) {
                Ui.printCustomMessage(OPEN_SQUARE_BRACKET + printIndex + CLOSE_SQUARE_BRACKET
                        + FOOD_HEADER + foodList.getFood(index).getFoodName()
                        + SPACE_FORMATTING + CAL_HEADER + foodList.getFood(index).getCalories());
                index++;
                printIndex++;
            }
        }
    }

    //View exercise
    private void viewExercise(ExerciseList exerciseList) {
        if (exerciseList.getSize() == 0) {
            Ui.printCustomMessage(EMPTY_EXERCISE_LIST);
        } else {
            int index = 0;
            int printIndex = index + 1;
            Ui.printCustomMessage(EXERCISE_LIST_HEADER);
            while (index < exerciseList.getSize()) {
                Ui.printCustomMessage(OPEN_SQUARE_BRACKET + printIndex + CLOSE_SQUARE_BRACKET
                        + EXERCISE_HEADER + exerciseList.getExercise(index).getNameOfExercise()
                        + SPACE_FORMATTING + BURNT_CAL_HEADER
                        + exerciseList.getExercise(index).getCalories());
                index++;
                printIndex++;
            }
        }
    }

    //View summary of total amount of calories consumed and burnt.
    private void viewSummary(FoodList foodList, ExerciseList exerciseList, User user) {
        Ui.printCustomMessage(CALORIE_CONSUMED_HEADER);
        Ui.printCustomMessage(String.valueOf(user.calculateCalorieConsumed(foodList).get()));
        Ui.printCustomMessage(CALORIE_BURNT_HEADER);
        Ui.printCustomMessage(String.valueOf(user.calculateCalorieBurnt(exerciseList).get()));
        Ui.printCustomMessage(NET_CALORIE_HEADER);
        Ui.printCustomMessage(String.valueOf(user.calculateCalorie(foodList, exerciseList).get()));
    }

    private void viewBmi(User user) {
        Ui.printCustomMessage(BMI_HEADER);
        String bmiString = String.format("%.2f", user.getBmi());
        Ui.printCustomMessage(bmiString);
    }

    private void viewProfile(User user) {
        Ui.printCustomMessage(USER_PROFILE_HEADER);
        Ui.printCustomMessage(user.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
