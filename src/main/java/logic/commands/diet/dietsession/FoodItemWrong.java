package logic.commands.diet.dietsession;

import logic.commands.Command;
import models.Food;
import exceptions.InvalidCommandWordException;
import logic.commands.CommandResult;
import storage.diet.DietStorage;

import java.util.ArrayList;

//@@author zsk612

/**
 * A representation of the command for wrong commands in diet session.
 */
public class FoodItemWrong extends Command {

    /**
     * Overrides execute for wrong command.
     *
     * @param input user input for command.
     * @param foodList arraylist that stored all the food items.
     * @param storage storage for diet session.
     * @param index Integer variable that shows the index of the session
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     */
    @Override
    public CommandResult execute(String input, ArrayList<Food> foodList,
                                 DietStorage storage, Integer index) throws InvalidCommandWordException {
        throw new InvalidCommandWordException();
    }
}
