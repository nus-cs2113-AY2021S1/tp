package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.task.Task;
import seedu.duke.calendar.task.Todo;


public class PrintTimelineCommand extends Command {
    public PrintTimelineCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        CalendarList timelineList = new CalendarList();
        CalendarList todoList = new CalendarList();

        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            CalendarItem temp = calendarList.getItem(i);
            if (temp instanceof Todo) {
                todoList.addItem(temp);
            } else {
                timelineList.addItem(temp);
            }
        }

        CalendarList sortedList = sortByDateTime(timelineList);

        System.out.println("Here is your timeline:");
        int numberOfItems = sortedList.getTotalItems();
        System.out.println("Timeline\n|");
        System.out.println(numberOfItems);
        for (int i = 0; i < numberOfItems; i++) {
            if (i == 0 || !(sortedList.getItem(i - 1).getDate().isEqual(sortedList.getItem(i).getDate()))) {
                System.out.println("|__ " + sortedList.getItem(i).getDate());
            }
            System.out.println("|        |_____ " + sortedList.getItem(i).getTime());
            System.out.println("|                 |_____ " + sortedList.getItem(i).getDescription());
        }
    }

    /**
     * Sort the items in a CalendarList in time sequence.
     * @param calendarList list to be sorted.
     * @return sorted CalendarList.
     */
    public CalendarList sortByDateTime(CalendarList calendarList) {
        CalendarList sortingList = calendarList;
        for (int i = 0; i < calendarList.getTotalItems(); i++) {
            for (int j = i + 1; j < calendarList.getTotalItems(); j++) {
                if (calendarList.getItem(i).getDate() != null && calendarList.getItem(j).getDate() != null) {
                    if (calendarList.getItem(j).getDate().isBefore(calendarList.getItem(i).getDate())) {
                        sortingList.swapTasks(i, j);
                    } else if (calendarList.getItem(j).getDate().isEqual(calendarList.getItem(i).getDate())) {
                        if (calendarList.getItem(i).getTime() != null && calendarList.getItem(j).getTime() != null) {
                            if (calendarList.getItem(j).getTime().isBefore(calendarList.getItem(i).getTime())) {
                                sortingList.swapTasks(i, j);
                            }
                        }
                    }
                }
            }
        }
        return sortingList;
    }
}
