package fitr.command;

import fitr.Calorie;
import fitr.Food;
import fitr.Recommender;
import fitr.exception.FitrException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_FOOD;

public class AddFoodCommand extends Command {
    public AddFoodCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                        User user, GoalList goalList, Recommender recommender) {
        try {
            String nameOfFood = command.split("/", 2)[0];
            nameOfFood = nameOfFood.trim();
            if (nameOfFood.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            command = command.split("/", 2)[1];
            if (command.split(" ").length == 1) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                if (amountOfCalories.get() < 0) {
                    throw new NumberFormatException();
                }
                foodList.addFood(new Food(nameOfFood, amountOfCalories));
                Ui.printCustomMessage("The following food has been added: " + nameOfFood);
            } else if (command.split(" ").length == 2) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                int amountOfFood = Integer.parseInt(command.split(" ", 2)[1]);
                if (amountOfCalories.get() < 0) {
                    throw new NumberFormatException();
                }
                if (amountOfFood < 0) {
                    throw new FitrException();
                }
                foodList.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood));
                Ui.printCustomMessage("The following food has been added: " + nameOfFood);
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError("Sorry, invalid calorie amount entered");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_FOOD);
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, the amount of food has to be a positive number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
