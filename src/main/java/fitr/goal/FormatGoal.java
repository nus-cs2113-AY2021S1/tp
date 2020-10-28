package fitr.goal;

import java.util.Objects;

public class FormatGoal {
    public static Goal formatGoal(String createdDate, String goalType, String goalDescription) {
        Goal newGoal = new Goal(createdDate, goalType, goalDescription);
        String descriptionPart = (goalType.equals("E")) ? "Burn" : "Eat";
        boolean isPositiveNumber = goalDescription.substring(1).trim().matches("\\d+");

        if (Objects.equals(goalDescription.split(" ", 2)[0].trim().charAt(0), '>')) {
            if (isPositiveNumber) {
                newGoal = new Goal(createdDate, goalType, descriptionPart + " more than "
                        + goalDescription.substring(1).trim() + " calories");
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } else if (Objects.equals(goalDescription.split(" ", 2)[0].trim().charAt(0), '<')) {
            if (isPositiveNumber) {
                newGoal = new Goal(createdDate, goalType, descriptionPart + " less than "
                        + goalDescription.substring(1).trim() + " calories");
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return newGoal;
    }
}
