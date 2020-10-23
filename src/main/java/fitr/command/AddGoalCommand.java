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
            String goalType = command.split(" ", 2)[0].trim();
            switch (goalType) {
            //Food goal
            case COMMAND_FOOD:
                command = command.split(" ", 2)[1].trim();
                Goal newFoodGoal = new Goal(createdDate, COMMAND_FOOD, command);
                goalList.addGoal(newFoodGoal);
                Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                        + newFoodGoal.getGoalType() + "] " + newFoodGoal.getDescription());
                break;
            //Exercise goal
            case COMMAND_EXERCISE:
                command = command.split(" ", 2)[1].trim();
                Goal newExerciseGoal = new Goal(createdDate, COMMAND_EXERCISE, command);
                goalList.addGoal(newExerciseGoal);
                Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                        + newExerciseGoal.getGoalType() + "] " + newExerciseGoal.getDescription());
                break;
            default:
                Ui.printFormatError(COMMAND_GOAL);
                break;
            }
            storageManager.writeGoalList(goalList);
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
}
