package fitr.command;

import fitr.Goal;
import fitr.Recommender;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Commands.COMMAND_CUSTOM;

public class AddGoalCommand extends Command {

    public AddGoalCommand(String command) {
        this.command = command;
    }


    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                        User user, GoalList goalList, Recommender recommender) {
        Ui.printCustomMessage("----------Goals Section-------------");
        Ui.printCustomMessage("The following are understood by Fitr:\n"
                + "'food'      format: food <goal description>\n"
                + "'exercise'  format: exercise <goal description>\n"
                + "'custom'    format: custom <goal description>\n"
                + "'back'      to return back to the main section");
        String userInput = Ui.read().toLowerCase().trim();
        while (!userInput.equals("back")) {
            try {
                String goalType = userInput.split(" ", 2)[0].trim();
                switch (goalType) {
                //Food goal
                case COMMAND_FOOD:
                    userInput = userInput.split(" ", 2)[1].trim();
                    Goal newFoodGoal = new Goal(COMMAND_FOOD, userInput);
                    goalList.addGoal(newFoodGoal);
                    Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                            + newFoodGoal.getGoalType() + "] " + newFoodGoal.getDescription());
                    break;
                //Exercise goal
                case COMMAND_EXERCISE:
                    userInput = userInput.split(" ", 2)[1].trim();
                    Goal newExerciseGoal = new Goal(COMMAND_EXERCISE, userInput);
                    goalList.addGoal(newExerciseGoal);
                    Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                            + newExerciseGoal.getGoalType() + "] " + newExerciseGoal.getDescription());
                    break;
                //Custom goal
                case COMMAND_CUSTOM:
                    userInput = userInput.split(" ", 2)[1].trim();
                    Goal newCustomGoal = new Goal(COMMAND_CUSTOM, userInput);
                    goalList.addGoal(newCustomGoal);
                    Ui.printCustomMessage("Okay! The following goal has been added: \n\t["
                            + newCustomGoal.getGoalType() + "] " + newCustomGoal.getDescription());
                    break;
                default:
                    Ui.printCustomError("Unrecognised input! Please input 'food', "
                            + "'exercise' or 'custom' to add the respective goal and 'back' exit the goal section");
                    break;
                }
                storage.writeGoalList(goalList);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printCustomError("Please input in the correct format!");
            } catch (IOException e) {
                Ui.printCustomError("Sorry, there is an error in the file");;
            }
            userInput = Ui.read().toLowerCase();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
