package fitr.goal;

import fitr.Goal;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class CheckGoalStatus {

    public static String checkGoalStatus(String status, Goal goal, FoodList foodList,
                                         ExerciseList exerciseList, User user) {
        int targetCalorie;
        int userConsumedCalorie = user.calculateCalorieConsumed(foodList, goal.getCreatedDate()).get();
        int userBurntCalorie = user.calculateCalorieBurnt(exerciseList, goal.getCreatedDate()).get();
        int calorieDifference;
        NumberFormat formatter = new DecimalFormat("#0.0");

        try {
            if (status.equals("✓")) {
                return status;
            }
            if (goal.getDescription().contains("Burn less than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userBurntCalorie;
                status = String.valueOf((calorieDifference < 0) ? 0.0 : 100.0);
            } else if (goal.getDescription().contains("Burn more than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userBurntCalorie;
                status = String.valueOf((calorieDifference < 0) ? 100.0 :
                        formatter.format((double) userBurntCalorie / (double) targetCalorie * 100));
            } else if (goal.getDescription().contains("Eat more than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = String.valueOf((calorieDifference < 0) ? 100.0 :
                        formatter.format((double) userConsumedCalorie / (double) targetCalorie * 100));
            } else if (goal.getDescription().contains("Eat less than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = String.valueOf((calorieDifference < 0) ? 0.0 : 100.0);
            }
        } catch (NumberFormatException e) {
            return status;
        }

        return status;
    }
}
