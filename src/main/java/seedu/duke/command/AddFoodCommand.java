package seedu.duke.command;

import seedu.duke.model.Food;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static seedu.duke.ui.Ui.displaySavedMessage;

//@@author e0425705
/**
 * Add activity food.
 */
public class AddFoodCommand extends Command {
    protected Food food;
    protected LocalDate date;

    /**
     * Adds food and it's respective calories.
     *
     * @param description food description
     * @param calories calories gained
     * @param isFromFile if data is from csv file
     */
    public AddFoodCommand(String description, int calories, boolean isFromFile) {
        this.food = new Food(description, calories, LocalDate.now(),isFromFile);
        this.date = LocalDateTime.now().toLocalDate();
        this.canBeChained = true;
    }

    /**
     * Adds food, it's respective calories and date.
     *
     * @param description food description
     * @param calories calories gained
     * @param isFromFile if data is from csv file
     * @param date date of activity
     */
    public AddFoodCommand(String description, int calories, boolean isFromFile, LocalDate date) {
        this.food = new Food(description, calories, date, isFromFile);
        this.date = date;
        this.canBeChained = true;
    }

    @Override
    public void execute() {
        dayMap.addActivity(date.atStartOfDay(), food);
        displaySavedMessage();
    }
}
