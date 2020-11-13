package seedu.duke.command;

import seedu.duke.model.Food;

import java.time.LocalDate;
import static seedu.duke.Trakcal.logging;

import static seedu.duke.ui.ExceptionMessages.displayEditIndexOutOfBoundsExceptionMessage;
import static seedu.duke.ui.Ui.displayEditMessage;
import static seedu.duke.ui.Ui.displaySavedMessage;

//@@author e0425705
/**
 * Edits food and its attributes at the indicated index.
 */
public class EditFoodCommand extends Command {
    protected int index;
    protected Food food;
    protected String description;
    protected int calories;
    protected LocalDate date;


    /**
     * Edits food and it's respective calories.
     *
     * @param description food description
     * @param calories calories gained
     */
    public EditFoodCommand(int index, String description, int calories) {
        this.index = index;
        this.description = description;
        this.calories = calories;
        this.canBeChained = true;
    }

    @Override
    public void execute() {
        try {
            LocalDate dateOfActivityToBeEdited = dayMap.getDateFromLastSeenListAtIndex(index);
            displayEditMessage();
            this.food = new Food(description, calories, dateOfActivityToBeEdited, false);
            dayMap.insertActivity(index, food);
            displaySavedMessage();
        } catch (IndexOutOfBoundsException e) {
            displayEditIndexOutOfBoundsExceptionMessage();
            logging.writeToLogWarning("Accessing an index that is out of bound!");
        }
    }
}
