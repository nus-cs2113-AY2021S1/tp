package logic.commands.diet.dietsession;

import logic.commands.Command;
import models.Food;
import logic.commands.CommandResult;
import storage.diet.DietStorage;
import ui.diet.dietsession.DietSessionUi;

import java.util.ArrayList;
import java.util.logging.Level;

//@@author zsk612
/**
 * A representation of the command for help commands in diet session.
 */
public class FoodItemHelp extends Command {

    /**
     * Overrides execute for help command to display help information for dietSession.
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
        StringBuilder helpMessage = new StringBuilder();
        DietSessionUi.printHelp(helpMessage);
        logger.log(Level.INFO, "Displayed help in dietSession");
        return new CommandResult(helpMessage.toString().trim());
    }
}
