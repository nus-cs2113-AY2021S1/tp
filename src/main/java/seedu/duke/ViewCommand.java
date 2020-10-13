package seedu.duke;

import static seedu.duke.common.Messages.ERROR_INVALID_VIEW_COMMAND;
import static seedu.duke.common.Messages.EMPTY_FOOD_LIST;
import static seedu.duke.common.Messages.EMPTY_EXERCISE_LIST;
import static seedu.duke.common.Messages.FOOD_LIST_HEADER;
import static seedu.duke.common.Messages.EXERCISE_LIST_HEADER;
import static seedu.duke.common.Messages.CALORIE_CONSUMED_HEADER;
import static seedu.duke.common.Messages.CALORIE_BURNT_HEADER;
import static seedu.duke.common.Messages.NET_CALORIE_HEADER;
import static seedu.duke.common.Messages.BMI_HEADER;
import static seedu.duke.common.Messages.USER_PROFILE_HEADER;

public class ViewCommand extends Command {
    private User user;
    private static UI ui = new UI();

    public ViewCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage) {
        user = new User();
        if (command.equalsIgnoreCase("view food")) {
            viewFood(foodList);
        } else if (command.equalsIgnoreCase("view exercise")) {
            viewExercise(exerciseList);
        } else if (command.equalsIgnoreCase("view summary")) {
            viewSummary(foodList, exerciseList);
        } else if (command.equalsIgnoreCase("view bmi")) {
            viewBmi(user);
        } else if (command.equalsIgnoreCase("view profile")) {
            viewProfile(user);
        } else {
            ui.printCustomMessage(ERROR_INVALID_VIEW_COMMAND);
        }
    }

    //View food
    public void viewFood(FoodList foodList) {
        if (foodList.getSize() == 0) {
            ui.printCustomMessage(EMPTY_FOOD_LIST);
        } else {
            int index = 0;
            int printIndex = index + 1;
            ui.printCustomMessage(FOOD_LIST_HEADER);
            while (index < foodList.getSize()) {
                System.out.println("[" + printIndex + "] " + "Food: " + foodList.getFood(index).getFoodName()
                        + "\n    Cal: " + foodList.getFood(index).getCalories());
                index++;
                printIndex++;
            }
        }
    }

    //View exercise
    public void viewExercise(ExerciseList exerciseList) {
        if (exerciseList.getSize() == 0) {
            ui.printCustomMessage(EMPTY_EXERCISE_LIST);
        } else {
            int index = 0;
            int printIndex = index + 1;
            ui.printCustomMessage(EXERCISE_LIST_HEADER);
            while (index < exerciseList.getSize()) {
                System.out.println("[" + printIndex + "] " + "Exercise: "
                        + exerciseList.getExercise(index).getNameOfExercise()
                        + "\n    Burnt Cal: " + exerciseList.getExercise(index).getCalories());
                index++;
                printIndex++;
            }
        }
    }

    //View summary of total amount of calories consumed and burnt.
    public void viewSummary(FoodList foodList, ExerciseList exerciseList) {
        ui.printCustomMessage(CALORIE_CONSUMED_HEADER);
        System.out.println(user.calculateCalorieConsumed(foodList).get());
        ui.printCustomMessage(CALORIE_BURNT_HEADER);
        System.out.println(user.calculateCalorieBurnt(exerciseList).get());
        ui.printCustomMessage(NET_CALORIE_HEADER);
        System.out.println(user.calculateCalorie(foodList, exerciseList).get());
    }

    public void viewBmi(User user) {
        ui.printCustomMessage(BMI_HEADER);
        System.out.println(user.getBmi());
    }

    public void viewProfile(User user) {
        ui.printCustomMessage(USER_PROFILE_HEADER);
        System.out.println(user);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
