package fitr.command;

import fitr.Calorie;
import fitr.Food;
import fitr.exception.FitrException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class AddFoodCommand extends Command {
    public AddFoodCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user) {
        command = command.split(" ", 2)[1];
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
                foodlist.addFood(new Food(nameOfFood, amountOfCalories));
                storage.writeFoodList(foodlist);
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
                foodlist.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood));
                storage.writeFoodList(foodlist);
                Ui.printCustomMessage("The following food has been added: " + nameOfFood);
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError("Sorry calories have to be a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Please key in the correct format");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, the amount of food has to be a positive number");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
