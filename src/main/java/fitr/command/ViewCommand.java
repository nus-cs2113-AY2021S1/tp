package fitr.command;

import fitr.common.DateManager;
import fitr.common.Messages;
import fitr.goal.Goal;
import fitr.exercise.Recommender;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.user.User;
import fitr.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_GOAL;
import static fitr.common.Commands.COMMAND_VIEW;
import static fitr.common.Commands.COMMAND_VIEW_BMI;
import static fitr.common.Commands.COMMAND_VIEW_PROFILE;
import static fitr.common.Commands.COMMAND_VIEW_SUMMARY;
import static fitr.common.Messages.EMPTY_FOOD_LIST;
import static fitr.common.Messages.EMPTY_EXERCISE_LIST;
import static fitr.common.Messages.EMPTY_GOAL_LIST;
import static fitr.common.Messages.FOOD_LIST_HEADER;
import static fitr.common.Messages.EXERCISE_LIST_HEADER;
import static fitr.common.Messages.CALORIE_CONSUMED_HEADER;
import static fitr.common.Messages.CALORIE_BURNT_HEADER;
import static fitr.common.Messages.GOAL_LIST_HEADER;
import static fitr.common.Messages.NET_CALORIE_HEADER;
import static fitr.common.Messages.BMI_HEADER;
import static fitr.common.Messages.PHRASE_EXTRA_PARAMETERS;
import static fitr.common.Messages.USER_PROFILE_HEADER;
import static fitr.common.Messages.OPEN_SQUARE_BRACKET;
import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.FOOD_HEADER;
import static fitr.common.Messages.QUANTITY_HEADER;
import static fitr.common.Messages.SPACE_FORMATTING;
import static fitr.common.Messages.CAL_HEADER;
import static fitr.common.Messages.EXERCISE_HEADER;
import static fitr.common.Messages.BURNT_CAL_HEADER;
import static fitr.common.Messages.DATE_HEADER;
import static fitr.common.Messages.EMPTY_STRING;
import static fitr.common.Messages.ERROR_INVALID_DATE;
import static fitr.common.Messages.EMPTY_EXERCISE_LIST_DATE;
import static fitr.common.Messages.EMPTY_FOOD_LIST_DATE;
import static fitr.common.Messages.NO_RECORDS_FOUND;
import static fitr.common.Messages.VIEW_FOOD_TIP;
import static fitr.common.Messages.VIEW_EXERCISE_TIP;
import static fitr.common.Messages.VIEW_SUMMARY_TIP;

public class ViewCommand extends Command {

    public ViewCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        if (command.equalsIgnoreCase(COMMAND_FOOD)) {
            viewFood(listManager.getFoodList());
        } else if (command.equalsIgnoreCase(COMMAND_EXERCISE)) {
            viewExercise(listManager.getExerciseList());
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_SUMMARY)) {
            viewSummary(listManager.getFoodList(), listManager.getExerciseList(), false);
        } else if (command.split(" ")[0].equalsIgnoreCase(COMMAND_VIEW_SUMMARY)) {
            if (command.split(" ").length > 2) {
                Ui.printFormatError(PHRASE_EXTRA_PARAMETERS);
                return;
            }
            viewSummaryByDate(listManager.getFoodList(), listManager.getExerciseList(), command.split(" ")[1]);
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_BMI)) {
            viewBmi(user);
        } else if (command.equalsIgnoreCase(COMMAND_VIEW_PROFILE)) {
            viewProfile(user);
        } else if (command.equalsIgnoreCase(COMMAND_GOAL)) {
            viewGoal(listManager.getFoodList(), listManager.getExerciseList(), listManager.getGoalList(), user);
        } else if (command.split(" ")[0].equalsIgnoreCase(COMMAND_EXERCISE)) {
            if (command.split(" ").length > 2) {
                Ui.printFormatError(PHRASE_EXTRA_PARAMETERS);
                return;
            }
            viewExerciseByDate(listManager.getExerciseList(), command.split(" ")[1], true);
        } else if (command.split(" ")[0].equalsIgnoreCase(COMMAND_FOOD)) {
            if (command.split(" ").length > 2) {
                Ui.printFormatError(PHRASE_EXTRA_PARAMETERS);
                return;
            }
            viewFoodByDate(listManager.getFoodList(), command.split(" ")[1], true);
        } else {
            Ui.printFormatError(COMMAND_VIEW);
        }
    }

    //View food
    private void viewFood(FoodList foodList) {
        if (foodList.getSize() == 0) {
            Ui.printCustomMessage(EMPTY_FOOD_LIST);
        } else {
            int index = 0;
            int printIndex = index + 1;
            String lastDate = EMPTY_STRING;
            Ui.printCustomMessage(FOOD_LIST_HEADER);
            while (index < foodList.getSize()) {
                if (!lastDate.equals(foodList.getFood(index).getDate())) {
                    if (index != 0) {
                        Ui.printCustomMessage(EMPTY_STRING);
                    }
                    Ui.printMessageInYellow(DATE_HEADER + foodList.getFood(index).getDate());
                    lastDate = foodList.getFood(index).getDate();
                    printIndex = 1;
                }
                Ui.printCustomMessage(OPEN_SQUARE_BRACKET + printIndex + CLOSE_SQUARE_BRACKET
                        + FOOD_HEADER + foodList.getFood(index).getFoodName()
                        + SPACE_FORMATTING + QUANTITY_HEADER + foodList.getFood(index).getAmountOfFood()
                        + SPACE_FORMATTING + CAL_HEADER + foodList.getFood(index).getCalories());
                index++;
                printIndex++;
            }
        }
        System.out.println("-".repeat(136));
        Ui.printMessageInBlue(VIEW_FOOD_TIP);
    }

    //View exercise
    private void viewExercise(ExerciseList exerciseList) {
        if (exerciseList.getSize() == 0) {
            Ui.printCustomMessage(EMPTY_EXERCISE_LIST);
        } else {
            int index = 0;
            int printIndex = index + 1;
            String lastDate = EMPTY_STRING;
            Ui.printCustomMessage(EXERCISE_LIST_HEADER);
            while (index < exerciseList.getSize()) {
                if (!lastDate.equals(exerciseList.getExercise(index).getDate())) {
                    if (index != 0) {
                        Ui.printCustomMessage(EMPTY_STRING);
                    }
                    Ui.printMessageInYellow(DATE_HEADER + exerciseList.getExercise(index).getDate());
                    lastDate = exerciseList.getExercise(index).getDate();
                    printIndex = 1;
                }
                Ui.printCustomMessage(OPEN_SQUARE_BRACKET + printIndex + CLOSE_SQUARE_BRACKET
                        + EXERCISE_HEADER + exerciseList.getExercise(index).getNameOfExercise()
                        + SPACE_FORMATTING + BURNT_CAL_HEADER + exerciseList.getExercise(index).getCalories());
                index++;
                printIndex++;
            }
        }
        System.out.println("-".repeat(136));
        Ui.printMessageInBlue(VIEW_EXERCISE_TIP);
    }

    private void viewSummary(FoodList foodList, ExerciseList exerciseList, Boolean isDate) {
        int foodIndex = 0;
        int exerciseIndex = 0;
        int totalCalorieConsumed = 0;
        int totalCalorieBurnt = 0;
        if (foodList.getSize() == 0 && exerciseList.getSize() == 0) {
            Ui.printCustomMessage(NO_RECORDS_FOUND);
        }
        String currentDate;
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> calorieList = new ArrayList<>();
        while (exerciseIndex < exerciseList.getSize() && foodIndex < foodList.getSize()) {
            if (Integer.parseInt(dateFormatter(exerciseList.getExercise(exerciseIndex).getDate()))
                    < Integer.parseInt(dateFormatter(foodList.getFood(foodIndex).getDate()))) {
                dateList.add(exerciseList.getExercise(exerciseIndex).getDate());
                calorieList.add(-exerciseList.getExercise(exerciseIndex).getCalories());
                exerciseIndex++;
            } else if (Integer.parseInt(dateFormatter(exerciseList.getExercise(exerciseIndex).getDate()))
                    == Integer.parseInt(dateFormatter(foodList.getFood(foodIndex).getDate()))) {
                dateList.add(exerciseList.getExercise(exerciseIndex).getDate());
                calorieList.add(-exerciseList.getExercise(exerciseIndex).getCalories());
                dateList.add(foodList.getFood(foodIndex).getDate());
                calorieList.add(foodList.getFood(foodIndex).getCalories());
                exerciseIndex++;
                foodIndex++;
            } else if (Integer.parseInt(dateFormatter(exerciseList.getExercise(exerciseIndex).getDate()))
                    > Integer.parseInt(dateFormatter(foodList.getFood(foodIndex).getDate()))) {
                dateList.add(foodList.getFood(foodIndex).getDate());
                calorieList.add(foodList.getFood(foodIndex).getCalories());
                foodIndex++;
            }
        }
        if (exerciseIndex >= exerciseList.getSize()) {
            while (foodIndex < foodList.getSize()) {
                calorieList.add(foodList.getFood(foodIndex).getCalories());
                dateList.add(foodList.getFood(foodIndex).getDate());
                foodIndex++;
            }
        } else {
            while (exerciseIndex < exerciseList.getSize()) {
                calorieList.add(-exerciseList.getExercise(exerciseIndex).getCalories());
                dateList.add(exerciseList.getExercise(exerciseIndex).getDate());
                exerciseIndex++;
            }
        }
        int index = 0;
        while (index < dateList.size()) {
            currentDate = dateList.get(index);
            Ui.printMessageInYellow(DATE_HEADER + currentDate);
            while (index < dateList.size() && dateList.get(index).equals(currentDate)) {
                if (calorieList.get(index) <= 0) {
                    totalCalorieBurnt += calorieList.get(index);//negative
                } else if (calorieList.get(index) > 0) {
                    totalCalorieConsumed += calorieList.get(index);//positive
                }
                index++;
            }
            Ui.printCustomMessage(CALORIE_CONSUMED_HEADER);
            Ui.printCustomMessage(String.valueOf(totalCalorieConsumed));
            Ui.printCustomMessage(CALORIE_BURNT_HEADER);
            Ui.printCustomMessage(String.valueOf(-totalCalorieBurnt));
            Ui.printCustomMessage(NET_CALORIE_HEADER);
            Ui.printCustomMessage(String.valueOf(totalCalorieBurnt + totalCalorieConsumed));
            totalCalorieBurnt = 0;
            totalCalorieConsumed = 0;
            if (dateList.size() != 1) {
                Ui.printCustomMessage("");
            }
        }
        if (!isDate) {
            System.out.println("-".repeat(136));
            Ui.printMessageInBlue(VIEW_SUMMARY_TIP);
        }
    }

    private void viewSummaryByDate(FoodList foodList, ExerciseList exerciseList, String date) {
        try {
            LocalDate.parse(date, DateManager.formatter);
            ExerciseList exerciseListByDate = viewExerciseByDate(exerciseList, date, false);
            FoodList foodListByDate = viewFoodByDate(foodList, date, false);
            viewSummary(foodListByDate, exerciseListByDate, true);
        } catch (Exception ex) {
            Ui.printCustomError(ERROR_INVALID_DATE);
        }
    }

    private String dateFormatter(String date) {
        //Convert date from DD/MM/YYYY to YYYYMMDD
        String newDateFormat;
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        newDateFormat = LocalDate.parse(date, DateManager.formatter).format(formatter2);
        return newDateFormat;
    }

    private void viewBmi(User user) {
        String bmiString = String.format("%.2f", user.getBmi());
        Ui.printCustomMessage(BMI_HEADER + bmiString);
    }

    private void viewProfile(User user) {
        Ui.printCustomMessage(USER_PROFILE_HEADER);
        Ui.printCustomMessage(user.toString());
    }

    private void viewGoal(FoodList foodList, ExerciseList exerciseList, GoalList goalList, User user) {
        if (goalList.getSize() == 0) {
            Ui.printCustomMessage(EMPTY_GOAL_LIST);
        } else {
            Ui.printCustomMessage(GOAL_LIST_HEADER);
            goalList = goalList.reformatGoalList(goalList, foodList, exerciseList, user);
            for (int i = 0; i < goalList.getSize(); i++) {
                Goal goal = goalList.getGoal(i);
                Ui.printCustomMessage((i + 1) + ". [" + goal.getGoalType() + "]["
                        + goal.getStatus(goal, foodList, exerciseList, user) + "] " + goal.getDescription()
                        + "\033[0;32m (created on: " + goal.getCreatedDate() + ")\033[0m");
            }
        }
    }

    public static ExerciseList viewExerciseByDate(ExerciseList exerciseList, String date, Boolean isPrint) {
        try {
            LocalDate.parse(date, DateManager.formatter);
        } catch (Exception ex) {
            if (isPrint) {
                Ui.printCustomError(ERROR_INVALID_DATE);
            }
            return null;
        }
        date = LocalDate.parse(date, DateManager.formatter).format(DateManager.formatter);
        ExerciseList exercisesOnThatDate = new ExerciseList();
        for (int i = 0; i < exerciseList.getSize(); i++) {
            if (date.equals(exerciseList.getExercise(i).getDate())) {
                exercisesOnThatDate.addExercise(exerciseList.getExercise(i));
            }
        }
        if (isPrint) {
            if (exercisesOnThatDate.getSize() == 0) {
                Ui.printCustomMessage(EMPTY_EXERCISE_LIST_DATE);
            } else {
                int index = 0;
                int printIndex = index + 1;
                Ui.printCustomMessage(EXERCISE_LIST_HEADER);
                Ui.printMessageInYellow(DATE_HEADER + date);
                while (index < exercisesOnThatDate.getSize()) {
                    Ui.printCustomMessage(OPEN_SQUARE_BRACKET + printIndex + CLOSE_SQUARE_BRACKET
                            + EXERCISE_HEADER + exercisesOnThatDate.getExercise(index).getNameOfExercise()
                            + SPACE_FORMATTING + BURNT_CAL_HEADER
                            + exercisesOnThatDate.getExercise(index).getCalories());
                    index++;
                    printIndex++;
                }
            }
        }
        return exercisesOnThatDate;
    }

    public static FoodList viewFoodByDate(FoodList foodList, String date, Boolean isPrint) {
        try {
            LocalDate.parse(date, DateManager.formatter);
        } catch (Exception ex) {
            if (isPrint) {
                Ui.printCustomError(ERROR_INVALID_DATE);
            }
            return null;
        }
        date = LocalDate.parse(date, DateManager.formatter).format(DateManager.formatter);
        FoodList foodOnThatDate = new FoodList();
        for (int i = 0; i < foodList.getSize(); i++) {
            if (date.equals(foodList.getFood(i).getDate())) {
                foodOnThatDate.addFood(foodList.getFood(i));
            }
        }
        if (isPrint) {
            if (foodOnThatDate.getSize() == 0) {
                Ui.printCustomMessage(EMPTY_FOOD_LIST_DATE);
            } else {
                int index = 0;
                int printIndex = index + 1;
                Ui.printCustomMessage(FOOD_LIST_HEADER);
                Ui.printMessageInYellow(DATE_HEADER + date);
                while (index < foodOnThatDate.getSize()) {
                    Ui.printCustomMessage(OPEN_SQUARE_BRACKET + printIndex + CLOSE_SQUARE_BRACKET
                            + FOOD_HEADER + foodOnThatDate.getFood(index).getFoodName()
                            + SPACE_FORMATTING + BURNT_CAL_HEADER + foodOnThatDate.getFood(index).getCalories());
                    index++;
                    printIndex++;
                }
            }
        }
        return foodOnThatDate;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
