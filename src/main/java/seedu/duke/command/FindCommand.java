package seedu.duke.command;


import seedu.duke.CommandException;
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
     * @throws CommandException if the keyword is not found or if the user inputs a wrong command.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws CommandException {
        assert userInput.startsWith(COMMAND_FIND_EVENT_OR_TASK) : "Find command invalid but passes";
        String[] command;
        String findCommand;
        try {
            command = userInput.split(" ", 2);
            findCommand = command[0];
        } catch (Exception e) {
            throw new CommandException("find");
        }

        switch (findCommand) {
        case COMMAND_FIND_EVENT:
            find(COMMAND_FIND_EVENT, calendarList);
            break;
        case COMMAND_FIND_TASK:
            find(COMMAND_FIND_TASK, calendarList);
            break;
        case COMMAND_FIND_EVENT_OR_TASK:
            find(COMMAND_FIND_EVENT_OR_TASK, calendarList);
            break;
        default:
            throw new CommandException("find");
        }
    }

    private void find(String command, CalendarList calendarList) throws CommandException {
        boolean isFound = false;
        String[] split;
        try {
            split = userInput.split(" ", 2);
            keyword = split[1].trim();
            if (keyword.isEmpty()) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new CommandException("missing keyword");
        }

        int itemCount = 0;
        switch (command) {
        case COMMAND_FIND_EVENT:
            for (int i = 0; i < calendarList.getTotalItems(); i++) {
                CalendarItem item = calendarList.getCalendarList().get(i);
                if (item instanceof Event) {
                    if (item.toString().toLowerCase().contains(keyword.toLowerCase())) {
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
                    if (item.toString().toLowerCase().contains(keyword.toLowerCase())) {
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
                if (item.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    itemCount++;
                    Ui.printFindTaskMessage(command, calendarList, isFound, i, itemCount);
                    isFound = true;
                }

            }
            break;
        default:
            break;
        }

        if (!isFound) {
            throw new CommandException("keyword not found");
        }


    }

}
