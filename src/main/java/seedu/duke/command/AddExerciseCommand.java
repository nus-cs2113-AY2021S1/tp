package seedu.duke.command;

import seedu.duke.model.Exercise;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static seedu.duke.ui.Ui.displaySavedMessage;

//@@author e0425705
/**
 * Add exercise.
 */
public class AddExerciseCommand extends Command {
    protected Exercise exercise;
    protected LocalDate date;

    /**
     * Adds exercise and it's respective calories.
     *
     * @param description exercise description
     * @param calories calories lost
     * @param isFromFile if data is from csv file
     */
    public AddExerciseCommand(String description, int calories, boolean isFromFile) {
        this.exercise = new Exercise(description, calories, LocalDate.now(), isFromFile);
        this.date = LocalDateTime.now().toLocalDate();
        this.canBeChained = true;
    }

    /**
     * Adds exercise, it's respective calories and date.
     *
     * @param description exercise description
     * @param calories calories lost
     * @param isFromFile if data is from csv file
     * @param date date of activity
     */
    public AddExerciseCommand(String description, int calories, boolean isFromFile, LocalDate date) {
        this.exercise = new Exercise(description, calories, date, isFromFile);
        this.date = date;
        this.canBeChained = true;
    }

    @Override
    public void execute() {
        dayMap.addActivity(date.atStartOfDay(), exercise);
        displaySavedMessage();
    }
}
