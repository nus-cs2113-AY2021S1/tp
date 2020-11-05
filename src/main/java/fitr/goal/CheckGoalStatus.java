package fitr.goal;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static fitr.common.Messages.KEYWORD_EXERCISE_LESS_THAN;
import static fitr.common.Messages.KEYWORD_EXERCISE_MORE_THAN;
import static fitr.common.Messages.KEYWORD_FOOD_LESS_THAN;
import static fitr.common.Messages.KEYWORD_FOOD_MORE_THAN;
import static fitr.common.Messages.SYMBOL_NO;
import static fitr.common.Messages.SYMBOL_YES;

public class CheckGoalStatus {

    public static String checkGoalStatus(String status, Goal goal, FoodList foodList,
                                         ExerciseList exerciseList, User user) {
        int targetCalorie;
        int userConsumedCalorie = user.calculateCalorieConsumed(foodList, goal.getCreatedDate()).get();
        int userBurntCalorie = user.calculateCalorieBurnt(exerciseList, goal.getCreatedDate()).get();
        int calorieDifference;
        NumberFormat formatter = new DecimalFormat("#0.0");

        try {
            if (status.equals(SYMBOL_YES)) {
                return status;
            }
            if (goal.getDescription().contains(KEYWORD_EXERCISE_LESS_THAN)) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userBurntCalorie;
                status = (calorieDifference < 0) ? SYMBOL_NO : SYMBOL_YES;
            } else if (goal.getDescription().contains(KEYWORD_EXERCISE_MORE_THAN)) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userBurntCalorie;
                status = String.valueOf((calorieDifference < 0) ? SYMBOL_YES :
                        formatter.format((double) userBurntCalorie / (double) targetCalorie * 100));
            } else if (goal.getDescription().contains(KEYWORD_FOOD_MORE_THAN)) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = String.valueOf((calorieDifference < 0) ? SYMBOL_YES :
                        formatter.format((double) userConsumedCalorie / (double) targetCalorie * 100));
            } else if (goal.getDescription().contains(KEYWORD_FOOD_LESS_THAN)) {
                targetCalorie = Integer.parseInt(goal.getDescription().split(" ")[3]);
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = (calorieDifference < 0) ? SYMBOL_NO : SYMBOL_YES;
            }
        } catch (NumberFormatException e) {
            return status;
        }

        return status;
    }
}
