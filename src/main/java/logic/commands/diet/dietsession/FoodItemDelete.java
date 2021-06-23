package logic.commands.diet.dietsession;

import logic.commands.Command;
import models.Food;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import storage.diet.DietStorage;
import ui.diet.dietsession.DietSessionUi;

import java.util.ArrayList;
import java.util.logging.Level;

//@@author zsk612
/**
 * A representation of the command for delete commands in diet session.
 */
public class FoodItemDelete extends Command {

    /**
     * Overrides execute for delete command to delete food items.
     *
     * @param input user input for command
     * @param foodList arraylist that stored all the food items
     * @param storage storage for diet session
     * @param index Integer variable that shows the index of the session
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     */
    @Override
    public CommandResult execute(String input, ArrayList<Food> foodList, DietStorage storage, Integer index) {
        String result = "";
        try {
            assert !input.isEmpty();
            int indexOfSession = Integer.parseInt(input);
            Food temp = foodList.get(indexOfSession - 1);
            result = "You have deleted " + temp.toString() + " from your list!";
            foodList.remove(temp);
            logger.log(Level.INFO, "Removed food from arraylist");
        } catch (IndexOutOfBoundsException e) {
            result = DietSessionUi.MESSAGE_NO_SUCH_INDEX;
            logger.log(Level.WARNING, "Did not input index");
        } catch (NumberFormatException e) {
            result = DietSessionUi.MESSAGE_DELETE_WRONG_FORMAT;
            logger.log(Level.WARNING, "Did not input correct index");
        }
        return new CommandResult(result, ExecutionResult.OK);
    }
}
