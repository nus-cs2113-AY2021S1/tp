package fitr.goal;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CheckGoalStatus {

    public static String checkGoalStatus(String status, Goal goal, FoodList foodList,
                                         ExerciseList exerciseList, User user) {
        int targetCalorie;
        int userConsumedCalorie = user.calculateCalorieConsumed(foodList, goal.getCreatedDate()).get();
        int userBurntCalorie = user.calculateCalorieBurnt(exerciseList, goal.getCreatedDate()).get();
        int calorieDifference;
        NumberFormat formatter = new DecimalFormat("#0.0");

        try {
            if (status.equals("Y")) {
                return status;
            }
            if (goal.getDescription().contains("Burn less than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userBurntCalorie;
                status = (calorieDifference < 0) ? "N" : "Y";
            } else if (goal.getDescription().contains("Burn more than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userBurntCalorie;
                status = String.valueOf((calorieDifference < 0) ? "Y" :
                        formatter.format((double) userBurntCalorie / (double) targetCalorie * 100));
            } else if (goal.getDescription().contains("Eat more than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = String.valueOf((calorieDifference < 0) ? "Y" :
                        formatter.format((double) userConsumedCalorie / (double) targetCalorie * 100));
            } else if (goal.getDescription().contains("Eat less than ")) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = (calorieDifference < 0) ? "N" : "Y";
            }
        } catch (NumberFormatException e) {
            return status;
        }

        return status;
    }
}
