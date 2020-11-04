package fitr.command;

import fitr.exception.FitrException;
import fitr.goal.Goal;
import fitr.exercise.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_GOAL;
import static fitr.common.DateManager.getCurrentDate;
import static fitr.common.Messages.ECHO_ADDED_GOAL;
import static fitr.common.Messages.ERROR_IN_FILE;
import static fitr.common.Messages.FORMAT_EXERCISE_GOAL;
import static fitr.common.Messages.FORMAT_FOOD_GOAL;
import static fitr.common.Messages.SYMBOL_EXERCISE;
import static fitr.common.Messages.SYMBOL_FOOD;
import static fitr.goal.FormatGoal.formatGoal;

public class AddGoalCommand extends Command {

    public AddGoalCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String goalType = command.split(" ", 2)[0].trim().toLowerCase();
            Goal newGoal = null;
            switch (goalType) {
            //Food goal
            case COMMAND_FOOD:
                try {
                    command = command.split(" ", 2)[1].trim();
                    newGoal = formatGoal(getCurrentDate(), SYMBOL_FOOD, command);
                    listManager.addGoal(newGoal);
                    Ui.printCustomMessage(ECHO_ADDED_GOAL + newGoal.getGoalType() + "] " + newGoal.getDescription());
                } catch (FitrException e) {
                    Ui.printFormatError("Smart food goal");
                }
                break;
            //Exercise goal
            case COMMAND_EXERCISE:
                try {
                    command = command.split(" ", 2)[1].trim();
                    newGoal = formatGoal(getCurrentDate(), SYMBOL_EXERCISE, command);

                    listManager.addGoal(newGoal);
                    Ui.printCustomMessage(ECHO_ADDED_GOAL + newGoal.getGoalType() + "] " + newGoal.getDescription());
                } catch (FitrException e) {
                    Ui.printFormatError("Smart exercise goal");
                }
                break;
            default:
                Ui.printFormatError(COMMAND_GOAL);
                break;
            }
            storageManager.writeGoalList(listManager.getGoalList(), listManager.getFoodList(),
                    listManager.getExerciseList(), user);
        } catch (IOException e) {
            Ui.printCustomError(ERROR_IN_FILE);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_GOAL);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
