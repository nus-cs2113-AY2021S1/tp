package fitr.goal;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.ui.Ui;
import fitr.user.User;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static fitr.common.Messages.KEYWORD_BURN;
import static fitr.common.Messages.KEYWORD_CALORIE;
import static fitr.common.Messages.KEYWORD_CALORIES;
import static fitr.common.Messages.KEYWORD_EAT;
import static fitr.common.Messages.KEYWORD_EXERCISE_LESS_THAN;
import static fitr.common.Messages.KEYWORD_EXERCISE_MORE_THAN;
import static fitr.common.Messages.KEYWORD_FOOD_LESS_THAN;
import static fitr.common.Messages.KEYWORD_FOOD_MORE_THAN;
import static fitr.common.Messages.KEYWORD_LESS;
import static fitr.common.Messages.KEYWORD_MORE;
import static fitr.common.Messages.KEYWORD_THAN;
import static fitr.common.Messages.SPACE_STRING;
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
            String[] goalWords = goal.getDescription().split(SPACE_STRING);
            if (goalWords.length != 5) {
                return status;
            }

            targetCalorie = Integer.parseInt(goalWords[3]);
            if(targetCalorie <= 0 || targetCalorie >= 100000) {
                return status;
            }
            if (goalWords[0].equals(KEYWORD_BURN) && goalWords[1].equals(KEYWORD_LESS) &&
                    goalWords[2].equals(KEYWORD_THAN)) {
                if ((targetCalorie == 1 && !goalWords[4].equals(KEYWORD_CALORIE))
                        && !goalWords[4].equals(KEYWORD_CALORIES)) {
                    return status;
                }
                calorieDifference = targetCalorie - userBurntCalorie;
                status = (calorieDifference < 0) ? SYMBOL_NO : SYMBOL_YES;
            } else if (goalWords[0].equals(KEYWORD_BURN) && goalWords[1].equals(KEYWORD_MORE) &&
                    goalWords[2].equals(KEYWORD_THAN)) {
                if ((targetCalorie == 1 && !goalWords[4].equals(KEYWORD_CALORIE))
                        && !goalWords[4].equals(KEYWORD_CALORIES)) {
                    return status;
                }
                calorieDifference = targetCalorie - userBurntCalorie;
                status = String.valueOf((calorieDifference < 0) ? SYMBOL_YES :
                        formatter.format((double) userBurntCalorie / (double) targetCalorie * 100));
            } else if (goalWords[0].equals(KEYWORD_EAT) && goalWords[1].equals(KEYWORD_MORE) &&
                    goalWords[2].equals(KEYWORD_THAN)) {
                if ((targetCalorie == 1 && !goalWords[4].equals(KEYWORD_CALORIE))
                        && !goalWords[4].equals(KEYWORD_CALORIES)) {
                    return status;
                }
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = String.valueOf((calorieDifference < 0) ? SYMBOL_YES :
                        formatter.format((double) userConsumedCalorie / (double) targetCalorie * 100));
            } else if (goalWords[0].equals(KEYWORD_EAT) && goalWords[1].equals(KEYWORD_LESS) &&
                    goalWords[2].equals(KEYWORD_THAN)) {
                if ((targetCalorie == 1 && !goalWords[4].equals(KEYWORD_CALORIE))
                        && !goalWords[4].equals(KEYWORD_CALORIES)) {
                    return status;
                }
                calorieDifference = targetCalorie - userConsumedCalorie;
                status = (calorieDifference < 0) ? SYMBOL_NO : SYMBOL_YES;
            }
        } catch (NumberFormatException e) {
            return status;
        }

        return status;
    }
}
