package seedu.duke.command;


import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;
import seedu.duke.calendar.task.Task;

/**
 * Searches the task list for tasks that contains the keyword specified by the user and prints them.
 */
public class FindCommand extends Command {
    public static final String COMMAND_FIND_EVENT = "/fe";
    public static final String COMMAND_FIND_TASK = "/ft";
    public static final String COMMAND_FIND_EVENT_OR_TASK = "/f";
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
        if (userInput.startsWith(COMMAND_FIND_EVENT)) {
            find(COMMAND_FIND_EVENT, calendarList);
        } else if (userInput.startsWith(COMMAND_FIND_TASK)) {
            find(COMMAND_FIND_TASK, calendarList);
        } else if (userInput.startsWith(COMMAND_FIND_EVENT_OR_TASK)) {
            find(COMMAND_FIND_EVENT_OR_TASK, calendarList);
        }

    }

    private void find(String command, CalendarList calendarList) throws DukeException {
        boolean isFound = false;
        keyword = userInput.replace(command, "").trim();

        if (keyword.isEmpty()) {
            throw new DukeException("keyword not found");
        }

        int itemCount = 0;
        switch (command) {
        case COMMAND_FIND_EVENT:
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem item = calendarList.getCalendarList().get(i);
                if (item instanceof Event) {
                    if (item.toString().contains(keyword)) {
                        itemCount++;
                        Ui.printFindTaskMessage(command, calendarList, isFound, i, itemCount);
                        isFound = true;
                    }
                }
            }

            break;
        case COMMAND_FIND_TASK:
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem item = calendarList.getCalendarList().get(i);
                if (item instanceof Task) {
                    if (item.toString().contains(keyword)) {
                        itemCount++;
                        Ui.printFindTaskMessage(command, calendarList, isFound, i, itemCount);
                        isFound = true;
                    }
                }
            }
            break;
        case COMMAND_FIND_EVENT_OR_TASK:
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem item = calendarList.getCalendarList().get(i);
                if (item.toString().contains(keyword)) {
                    itemCount++;
                    Ui.printFindTaskMessage(command, calendarList, isFound, i, itemCount);
                    isFound = true;
                }

            }
            break;
        }

        if (!isFound) {
            throw new DukeException("keyword not found");
        }


    }

}
