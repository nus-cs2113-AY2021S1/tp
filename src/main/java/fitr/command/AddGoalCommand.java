package fitr.command;

import fitr.Goal;
import fitr.Recommender;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;
import java.util.Objects;

import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_GOAL;

public class AddGoalCommand extends Command {
    protected String createdDate;

    public AddGoalCommand(String command, String createdDate) {
        this.command = command;
        this.createdDate = createdDate;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
        try {
            String goalType = command.split(" ", 2)[0].trim().toLowerCase();
            Goal newGoal;
            switch (goalType) {
            //Food goal
            case COMMAND_FOOD:
                command = command.split(" ", 2)[1].trim();
                newGoal = checkShortcut("F", command);
                goalList.addGoal(newGoal);
                Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                        + newGoal.getGoalType() + "] " + newGoal.getDescription());
                break;
            //Exercise goal
            case COMMAND_EXERCISE:
                command = command.split(" ", 2)[1].trim();
                newGoal = checkShortcut("E", command);
                goalList.addGoal(newGoal);
                Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                        + newGoal.getGoalType() + "] " + newGoal.getDescription());
                break;
            default:
                Ui.printFormatError(COMMAND_GOAL);
                break;
            }
            storageManager.writeGoalList(goalList, foodList, exerciseList, user);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Please input in the correct format!");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private Goal checkShortcut(String goalType, String command) {
        Goal newGoal = new Goal(createdDate, goalType, command);
        String descriptionPart = (goalType == "E") ? "Burn" : "Eat";
        boolean isPositiveNumber = command.substring(1).trim().matches("\\d+");

        if (Objects.equals(command.split(" ", 2)[0].trim().charAt(0), '>')) {
            if (isPositiveNumber) {
                newGoal = new Goal(createdDate, goalType, descriptionPart + " more than "
                        + command.substring(1).trim() + " calories");
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } else if (Objects.equals(command.split(" ", 2)[0].trim().charAt(0), '<')) {
            if (isPositiveNumber) {
                newGoal = new Goal(createdDate, goalType, descriptionPart + " less than "
                        + command.substring(1).trim() + " calories");
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return newGoal;
    }
}
