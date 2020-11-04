package fitr.command;

import fitr.calorie.Calorie;
import fitr.food.Food;
import fitr.exercise.Recommender;
import fitr.exception.FitrException;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.Commands.COMMAND_FOOD;
import static fitr.common.DateManager.getCurrentDate;
import static fitr.common.Messages.ECHO_ADDED_FOOD;
import static fitr.common.Messages.ERROR_INVALID_CALORIE;
import static fitr.common.Messages.ERROR_IN_FILE;
import static fitr.common.Messages.FOOD_NAME_HEADER;
import static fitr.common.Messages.LINE_BREAK;

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
                Ui.printCustomMessage(ECHO_ADDED_FOOD
                        + FOOD_NAME_HEADER + nameOfFood + LINE_BREAK
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
                Ui.printCustomMessage(ECHO_ADDED_FOOD
                        + FOOD_NAME_HEADER + nameOfFood + LINE_BREAK
                        + "Calorie Consumed: " + amountOfCalories.get());
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError(ERROR_INVALID_CALORIE);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_FOOD);
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, the amount of food has to be a positive number");
        } catch (IOException e) {
            Ui.printCustomError(ERROR_IN_FILE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
