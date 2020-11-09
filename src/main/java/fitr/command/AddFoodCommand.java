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
import static fitr.common.Messages.PHRASE_EXTRA_PARAMETERS;
import static fitr.common.Messages.SPLIT_SPACE;

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
            if (command.split(SPLIT_SPACE).length == 1) {
                Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(SPLIT_SPACE)[0]));
                if (amountOfCalories.get() < 0 || amountOfCalories.get() > 10000) {
                    throw new NumberFormatException();
                }
                listManager.addFood(new Food(nameOfFood, amountOfCalories, getCurrentDate()));
                storageManager.writeFoodList(listManager.getFoodList());
                Ui.printCustomMessage(ECHO_ADDED_FOOD
                        + FOOD_NAME_HEADER + nameOfFood + LINE_BREAK
                        + "Calorie Consumed: " + amountOfCalories.get()
                );
            } else if (command.split(SPLIT_SPACE).length == 2) {
                String individualCalorie = command.split(SPLIT_SPACE, 2)[0];
                if (!individualCalorie.matches("\\d+")) {
                    throw new NumberFormatException();
                }
                if (!command.split(" ", 2)[1].matches("\\d+")) {
                    throw new FitrException();
                }
                int amountOfFood = Integer.parseInt(command.split(SPLIT_SPACE, 2)[1]);
                if (amountOfFood < 1 || amountOfFood > 1000) {
                    throw new FitrException();
                }
                if (Integer.parseInt(individualCalorie) < 0 || Integer.parseInt(individualCalorie) > 10000) {
                    throw new NumberFormatException();
                }
                Calorie amountOfCalories = new Calorie(Integer.parseInt(individualCalorie)
                        * amountOfFood);
                listManager.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood, getCurrentDate()));
                storageManager.writeFoodList(listManager.getFoodList());
                Ui.printCustomMessage(ECHO_ADDED_FOOD
                        + FOOD_NAME_HEADER + nameOfFood + LINE_BREAK
                        + "Calorie Consumed: " + amountOfCalories.get());
            } else {
                Ui.printFormatError(PHRASE_EXTRA_PARAMETERS);
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError(ERROR_INVALID_CALORIE);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_FOOD);
        } catch (FitrException e) {
            Ui.printCustomError("Sorry, quantity should be a positive integer less than or equal to 1000!");
        } catch (IOException e) {
            Ui.printCustomError(ERROR_IN_FILE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
