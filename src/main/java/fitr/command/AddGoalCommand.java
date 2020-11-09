package fitr.command;

import fitr.exception.FitrException;
import fitr.exception.UpperBoundLessThanException;
import fitr.exception.UpperBoundMoreThanException;
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

import static fitr.common.Messages.ADD_SMART_EXERCISE_GOAL_TIP;
import static fitr.common.Messages.ADD_SMART_FOOD_GOAL_TIP;
import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.ECHO_ADDED_GOAL;
import static fitr.common.Messages.ERROR_GOAL_LESS_THAN_UPPERBOUND;
import static fitr.common.Messages.ERROR_GOAL_MORE_THAN_UPPERBOUND;
import static fitr.common.Messages.ERROR_IN_FILE;
import static fitr.common.Messages.PHRASE_SMART_EXERCISE_GOAL;
import static fitr.common.Messages.PHRASE_SMART_FOOD_GOAL;
import static fitr.common.Messages.SEPARATOR_LINE;
import static fitr.common.Messages.SPACE_STRING;
import static fitr.common.Messages.SYMBOL_EXERCISE;
import static fitr.common.Messages.SYMBOL_FOOD;

import static fitr.common.DateManager.getCurrentDate;
import static fitr.goal.FormatGoal.formatGoal;

public class AddGoalCommand extends Command {

    public AddGoalCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String goalType = command.split(SPACE_STRING, 2)[0].trim().toLowerCase();
            Goal newGoal = null;
            switch (goalType) {
            //Food goal
            case COMMAND_FOOD:
                try {
                    command = command.split(SPACE_STRING, 2)[1].trim();
                    newGoal = formatGoal(getCurrentDate(), SYMBOL_FOOD, command);
                    listManager.addGoal(newGoal);
                    Ui.printCustomMessage(ECHO_ADDED_GOAL + newGoal.getGoalType() + CLOSE_SQUARE_BRACKET
                            + SPACE_STRING + newGoal.getDescription());
                    if (newGoal.getDescription().equals(command)) {
                        Ui.printCustomMessage(SEPARATOR_LINE);
                        Ui.printMessageInBlue(ADD_SMART_FOOD_GOAL_TIP);
                    }
                } catch (FitrException e) {
                    Ui.printFormatError(PHRASE_SMART_FOOD_GOAL);
                }
                break;
            //Exercise goal
            case COMMAND_EXERCISE:
                try {
                    command = command.split(SPACE_STRING, 2)[1].trim();
                    newGoal = formatGoal(getCurrentDate(), SYMBOL_EXERCISE, command);
                    listManager.addGoal(newGoal);
                    Ui.printCustomMessage(ECHO_ADDED_GOAL + newGoal.getGoalType() + CLOSE_SQUARE_BRACKET
                            + SPACE_STRING + newGoal.getDescription());
                    if (newGoal.getDescription().equals(command)) {
                        Ui.printCustomMessage(SEPARATOR_LINE);
                        Ui.printMessageInBlue(ADD_SMART_EXERCISE_GOAL_TIP);
                    }
                } catch (FitrException e) {
                    Ui.printFormatError(PHRASE_SMART_EXERCISE_GOAL);
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
        } catch (UpperBoundLessThanException e) {
            Ui.printCustomError(ERROR_GOAL_LESS_THAN_UPPERBOUND);
        } catch (UpperBoundMoreThanException e) {
            Ui.printCustomError(ERROR_GOAL_MORE_THAN_UPPERBOUND);;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
