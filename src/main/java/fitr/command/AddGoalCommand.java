package fitr.command;

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
import static fitr.goal.FormatGoal.formatGoal;

public class AddGoalCommand extends Command {
    protected String createdDate;

    public AddGoalCommand(String command, String createdDate) {
        this.command = command;
        this.createdDate = createdDate;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String goalType = command.split(" ", 2)[0].trim().toLowerCase();
            Goal newGoal;
            switch (goalType) {
            //Food goal
            case COMMAND_FOOD:
                command = command.split(" ", 2)[1].trim();
                newGoal = formatGoal(createdDate, "F", command);
                listManager.addGoal(newGoal);
                Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                        + newGoal.getGoalType() + "] " + newGoal.getDescription());
                break;
            //Exercise goal
            case COMMAND_EXERCISE:
                command = command.split(" ", 2)[1].trim();
                newGoal = formatGoal(createdDate, "E", command);
                listManager.addGoal(newGoal);
                Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                        + newGoal.getGoalType() + "] " + newGoal.getDescription());
                break;
            default:
                Ui.printFormatError(COMMAND_GOAL);
                break;
            }
            storageManager.writeGoalList(listManager.getGoalList(), listManager.getFoodList(),
                    listManager.getExerciseList(), user);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Please input in the correct format!");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
