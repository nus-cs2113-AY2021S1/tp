package fitr.command;

import fitr.Calorie;
import fitr.Food;
import fitr.Recommender;
import fitr.exception.FitrException;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.DateManager.getCurrentDate;

public class AddFoodCommand extends Command {
    public AddFoodCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String nameOfFood = command.split("/", 2)[0].trim();
            if (nameOfFood.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            command = command.split("/", 2)[1].trim();
            if (command.split(" ").length == 1) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                if (amountOfCalories.get() < 0) {
                    throw new NumberFormatException();
                }
                listManager.addFood(new Food(nameOfFood, amountOfCalories, getCurrentDate()));
                storageManager.writeFoodList(listManager.getFoodList());
                Ui.printCustomMessage("The following food has been added:\n"
                        + "Name of Food: " + nameOfFood + "\n"
                        + "Calorie Consumed: " + amountOfCalories.get()
                );
            } else if (command.split(" ").length == 2) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0])
                        * Integer.parseInt(command.split(" ")[1]));
                int amountOfFood = Integer.parseInt(command.split(" ", 2)[1]);
                if (amountOfCalories.get() < 0) {
                    throw new NumberFormatException();
                }
                if (amountOfFood < 0) {
                    throw new FitrException();
                }
                listManager.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood, getCurrentDate()));
                storageManager.writeFoodList(listManager.getFoodList());
                Ui.printCustomMessage("The following food has been added:\n"
                        + "Name of Food: " + nameOfFood + "\n"
                        + "Calorie Consumed: " + amountOfCalories.get());
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError("Sorry, invalid calorie amount entered");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_FOOD);
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, the amount of food has to be a positive number");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
