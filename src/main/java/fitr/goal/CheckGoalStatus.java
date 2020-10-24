package fitr.goal;

import fitr.Goal;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CheckGoalStatus {

    public static String checkGoalStatus(Goal goal, FoodList foodList, ExerciseList exerciseList, User user) {
        int targetCalorie, userCalorie, calorieDifference;
        String status = "unknown!";
        NumberFormat formatter = new DecimalFormat("#0.0");
        if (goal.getDescription().contains("Burn less than ")) {
            targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
            userCalorie = user.calculateCalorieBurnt(exerciseList).get();
            calorieDifference = targetCalorie - userCalorie;
            status = String.valueOf((calorieDifference < 0) ? 0.0 : 100.0);
        } else if (goal.getDescription().contains("Burn more than ")) {
            targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
            userCalorie = user.calculateCalorieBurnt(exerciseList).get();
            calorieDifference = targetCalorie - userCalorie;
            status = String.valueOf((calorieDifference < 0) ? 100.00 : formatter.format((double)userCalorie / (double)targetCalorie * 100));
        } else if (goal.getDescription().contains("Eat more than ")) {
            targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
            userCalorie = user.calculateCalorieConsumed(foodList).get();
            calorieDifference = targetCalorie - userCalorie;
            status = String.valueOf((calorieDifference < 0) ? 100.00 : formatter.format((double)userCalorie / (double)targetCalorie * 100));
        } else if (goal.getDescription().contains("Eat less than ")) {
            targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
            userCalorie = user.calculateCalorieConsumed(foodList).get();
            calorieDifference = targetCalorie - userCalorie;
            status = String.valueOf((calorieDifference < 0) ? 0.00 : 100.00);
        }

        return status;
    }
}
