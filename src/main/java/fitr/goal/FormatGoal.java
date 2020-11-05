package fitr.goal;

import fitr.exception.FitrException;
import fitr.ui.Ui;

import java.time.LocalDate;
import java.util.Objects;

import static fitr.common.Messages.SYMBOL_EXERCISE;

public class FormatGoal {
    public static Goal formatGoal(LocalDate createdDate, String goalType, String goalDescription) throws FitrException {
        Goal newGoal = new Goal(createdDate, goalType, goalDescription);
        String descriptionPart = (goalType.equals(SYMBOL_EXERCISE)) ? "Burn" : "Eat";
        String[] arguments = goalDescription.substring(1).trim().split(" ");
        boolean isPositiveNumber = arguments[0].matches("\\d+");

        if (Objects.equals(goalDescription.split(" ", 2)[0].trim().charAt(0), '>')) {
            if (isPositiveNumber) {
                if (arguments.length != 1) {
                    throw new FitrException();
                }
                newGoal = new Goal(createdDate, goalType, descriptionPart + " more than "
                        + goalDescription.substring(1).trim() + " calories");
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } else if (Objects.equals(goalDescription.split(" ", 2)[0].trim().charAt(0), '<')) {
            if (isPositiveNumber) {
                if (arguments.length != 1) {
                    throw new FitrException();
                }
                newGoal = new Goal(createdDate, goalType, descriptionPart + " less than "
                        + goalDescription.substring(1).trim() + " calories");
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return newGoal;
    }
}
