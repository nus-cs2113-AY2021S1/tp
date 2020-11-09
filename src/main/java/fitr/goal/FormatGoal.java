package fitr.goal;

import fitr.exception.FitrException;
import fitr.exception.UpperBoundLessThanException;
import fitr.exception.UpperBoundMoreThanException;

import java.time.LocalDate;
import java.util.Objects;

import static fitr.common.Messages.EMPTY_STRING;
import static fitr.common.Messages.KEYWORD_BURN;
import static fitr.common.Messages.KEYWORD_CALORIE;
import static fitr.common.Messages.KEYWORD_CALORIES;
import static fitr.common.Messages.KEYWORD_EAT;
import static fitr.common.Messages.PHRASE_LESS_THAN;
import static fitr.common.Messages.PHRASE_MORE_THAN;
import static fitr.common.Messages.SPACE_STRING;
import static fitr.common.Messages.SPLIT_SPACE;
import static fitr.common.Messages.SYMBOL_EXERCISE;
import static fitr.common.Messages.SYMBOL_LESS_THAN;
import static fitr.common.Messages.SYMBOL_MORE_THAN;

public class FormatGoal {
    public static Goal formatGoal(LocalDate createdDate, String goalType, String goalDescription)
            throws FitrException, UpperBoundLessThanException, UpperBoundMoreThanException {
        Goal newGoal = new Goal(createdDate, goalType, goalDescription);
        String descriptionPart = (goalType.equals(SYMBOL_EXERCISE)) ? KEYWORD_BURN : KEYWORD_EAT;
        String[] arguments = goalDescription.substring(1).trim().split(SPLIT_SPACE);
        boolean isPositiveNumber = arguments[0].matches("^(|-?\\d+)$");

        if (Objects.equals(goalDescription.split(SPLIT_SPACE, 2)[0].trim().charAt(0), SYMBOL_MORE_THAN)) {
            if (isPositiveNumber) {
                if (arguments.length != 1) {
                    throw new FitrException();
                }
                String targetCalories = removeLeadingZeros(goalDescription.substring(1).trim());
                if (Integer.parseInt(targetCalories) >= 100000 || Integer.parseInt(targetCalories) < 0) {
                    throw new UpperBoundMoreThanException();
                }
                String wordCalorie = (Integer.parseInt(targetCalories) == 1) ? (SPACE_STRING + KEYWORD_CALORIE)
                        : (SPACE_STRING + KEYWORD_CALORIES);
                newGoal = new Goal(createdDate, goalType, descriptionPart + SPACE_STRING
                        + PHRASE_MORE_THAN + SPACE_STRING + targetCalories + wordCalorie);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } else if (Objects.equals(goalDescription.split(SPLIT_SPACE, 2)[0].trim().charAt(0), SYMBOL_LESS_THAN)) {
            if (isPositiveNumber) {
                if (arguments.length != 1) {
                    throw new FitrException();
                }
                String targetCalories = removeLeadingZeros(goalDescription.substring(1).trim());
                if (Integer.parseInt(targetCalories) >= 100000 || Integer.parseInt(targetCalories) <= 0) {
                    throw new UpperBoundLessThanException();
                }
                String wordCalorie = (Integer.parseInt(targetCalories) == 1) ? (SPACE_STRING + KEYWORD_CALORIE)
                        : (SPACE_STRING + KEYWORD_CALORIES);
                newGoal = new Goal(createdDate, goalType, descriptionPart + SPACE_STRING
                        + PHRASE_LESS_THAN + SPACE_STRING + targetCalories + wordCalorie);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return newGoal;
    }

    private static String removeLeadingZeros(String numberOfCalories) {
        String strPattern = "^0+(?!$)";
        numberOfCalories = numberOfCalories.replaceAll(strPattern, EMPTY_STRING);
        return numberOfCalories;
    }
}
