package fitr.command;

import fitr.Calorie;
import fitr.common.Commands;
import fitr.common.Messages;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditCommand extends Command {
    public static final Pattern ARGUMENT_FORMAT = Pattern.compile("(?<argument>\\S+)(?<index>.*)");

    public EditCommand(String arguments) {
        command = arguments.toLowerCase();
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage, User user) {
        Matcher matcher = ARGUMENT_FORMAT.matcher(command.trim());
        int index;

        if (!matcher.matches()) {
            Ui.printInvalidCommandError();
            return;
        }

        String argument = matcher.group("argument");

        switch (argument) {
        case Messages.EDIT_NAME:
            editName(user);
            break;
        case Messages.EDIT_HEIGHT:
            editHeight(user);
            break;
        case Messages.EDIT_WEIGHT:
            editWeight(user);
            break;
        case Messages.EDIT_AGE:
            editAge(user);
            break;
        case Messages.EDIT_GENDER:
            editGender(user);
            break;
        case Commands.COMMAND_EXERCISE:
            index = Integer.parseInt(matcher.group("index").trim());
            editExercise(exerciseList, index);
            break;
        case Commands.COMMAND_FOOD:
            index = Integer.parseInt(matcher.group("index").trim());
            editFood(foodList, index);
            break;
        default:
            Ui.printInvalidCommandError();
            break;
        }

        try {
            storage.writeUserConfigFile(user);
            storage.writeExerciseList(exerciseList);
            storage.writeFoodList(foodList);
        } catch (IOException e) {
            Ui.printCustomMessage(Messages.MISSING_FILE);

        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void editName(User user) {
        Ui.printCustomMessage(Messages.EDIT_NAME_HEADER);
        user.setName();
        Ui.printCustomMessage(Messages.NAME_ECHO_HEADER + user.getName());
    }

    private void editHeight(User user) {
        Ui.printCustomMessage(Messages.EDIT_HEIGHT_HEADER);
        user.setupHeight();
        Ui.printCustomMessage(Messages.HEIGHT_ECHO_HEADER + user.getHeight());
    }

    private void editWeight(User user) {
        Ui.printCustomMessage(Messages.EDIT_WEIGHT_HEADER);
        user.setupWeight();
        Ui.printCustomMessage(Messages.WEIGHT_ECHO_HEADER + user.getWeight());
    }

    private void editAge(User user) {
        Ui.printCustomMessage(Messages.EDIT_AGE_HEADER);
        user.setupAge();
        Ui.printCustomMessage(Messages.AGE_ECHO_HEADER + user.getAge());
    }

    private void editGender(User user) {
        Ui.printCustomMessage(Messages.EDIT_GENDER_HEADER);
        user.setupGender();
        Ui.printCustomMessage(Messages.GENDER_ECHO_HEADER + user.getGender());
    }

    private void editExercise(ExerciseList exerciseList, int index) {
        if (exerciseList.getSize() == 0) {
            Ui.printCustomError("Exercise list is empty!");
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
        exerciseList.getExercise(index - 1).setCaloricBurnRate();

        Ui.printCustomMessage("Enter new duration "
                + "[previous: " + exerciseList.getExercise(index - 1).getDuration() + "]");
        int newDuration = Integer.parseInt(Ui.read());
        exerciseList.getExercise(index - 1).setDurationOfExercise(newDuration);

        Ui.printCustomMessage("Successfully edited exercise!");
    }

    private void editFood(FoodList foodList, int index) {
        if (foodList.getSize() == 0) {
            Ui.printCustomError("Food list is empty!");
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
        foodList.getFood(index - 1).setCaloricRate();

        Ui.printCustomMessage("Enter new quantity "
                + "[previous: " + foodList.getFood(index - 1).getAmountOfFood() + "]");
        int newFoodQuantity = Integer.parseInt(Ui.read());
        foodList.getFood(index - 1).setAmountOfFood(newFoodQuantity);

        Ui.printCustomMessage("Successfully edited food!");
    }
}
