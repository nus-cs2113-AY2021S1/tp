package seedu.duke.command;


import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;

/**
 * Searches the task list for tasks that contains the keyword specified by the user and prints them.
 */
public class FindCommand extends Command {
    private String keyword;


    public FindCommand(String userInput) {
        super(userInput);
    }

    /**
     * Searches the calendar list for tasks that contains the keyword specified by the user and prints them.
     *
     * @param calendarList the calendar list to search from.
     * @param storage      not required.
     * @throws DukeException if the keyword is not found.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {

        keyword = userInput.replace("find", "").trim();

        if (keyword.isEmpty()) {
            throw new DukeException("keyword not found");
        }

        try {
            Ui.printFindTaskMessage(calendarList, keyword);
        } catch (Exception e) {
            throw new DukeException("keyword not found");
        }
    }

}
